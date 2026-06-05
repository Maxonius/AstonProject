package io;

import java.util.*;
import java.io.*;
import java.lang.reflect.Field;

import collection.CustomArrayList;

public class RWsystem {
    public static <T> void appendToCSV(CustomArrayList<T> collection, String fName)
            throws IOException {
        boolean isExists = new File(fName).exists();

        try (FileWriter fw = new FileWriter(fName, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter pw = new PrintWriter(bw)) {
            for (int i = 0; i < collection.size(); i++) {
                T element = collection.get(i);
                String wrLine = objectToCSVLine(element);
                pw.println(wrLine);
            }
        }
    }

    private static <T> boolean isSystemClass(T obj) {
        String className = obj.getClass().getPackage().getName();
        // Классы из стандартной библиотеки Java
        return className.startsWith("java.") ||
                className.startsWith("javax.") ||
                className.startsWith("sun.") ||
                obj instanceof String ||
                obj instanceof Number ||
                obj instanceof Boolean ||
                obj instanceof Character;
    }

    private static String escapeForCSV(String value) {
        if (value == null) {
            return "";
        }

        // Если значение содержит запятые, кавычки или переносы строк - оборачиваем в кавычки
        if (value.contains(",") || value.contains("\"") ||
                value.contains("\n") || value.contains("\r")) {
            // Экранируем кавычки ("" вместо ")
            String escaped = value.replace("\"", "\"\"");
            return "\"" + escaped + "\"";
        }

        return value;
    }

    private static <T> String objectToCSVLine(T obj) {
        if (obj == null) {
            return "";
        }

        if (isSystemClass(obj)) {
            return escapeForCSV(obj.toString());
        }

        List<String> vals = new ArrayList<>();
        Field[] fields = obj.getClass().getDeclaredFields();

        try {
            for (Field f : fields) {
                f.setAccessible(true);
                Object val = f.get(obj);
                String strValue = val != null ? val.toString() : "";

                vals.add(escapeForCSV(strValue));
            }
        } catch (IllegalAccessException e) {
            return escapeForCSV(obj.toString());
        }

        return String.join(",", vals);
    }

    public static <T> void loadFromCSV(CustomArrayList<T> collection,
                                       String fName,
                                       IConvert<T> converter)
            throws IOException {

        if (collection == null || converter == null) {
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(fName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String value = unescapeFromCSV(line);
                T obj = converter.convert(value);
                if (obj != null) {
                    collection.add(obj);
                }
            }
        }
    }

    private static String unescapeFromCSV(String value) {
        if (value == null || value.isEmpty()) {
            return "";
        }

        if (value.startsWith("\"") && value.endsWith("\"")) {
            value = value.substring(1, value.length() - 1);
            value = value.replace("\"\"", "\"");
        }

        return value;
    }
}
