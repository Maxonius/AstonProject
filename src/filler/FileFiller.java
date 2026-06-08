package filler;

import collection.CustomArrayList;
import model.Car;
import io.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileFiller implements DataFiller{
    private final File file;

    public FileFiller(File file) {
        this.file = file;
    }

    @Override
    public CustomArrayList<Car> fill(int count){
        CustomArrayList<Car> res = new CustomArrayList<>();

        if (file == null || !file.exists()) {
            System.out.println("[ОШИБКА]: такого файла не существует: " + file);
            return res;
        }

        // Читаем файл и преобразуем строки в объекты Car
        List<Car> carList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            int loaded = 0;

            while ((line = br.readLine()) != null && (count <= 0 || loaded < count)) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                Car car = parseLineToCar(line);
                if (car != null) {
                    carList.add(car);
                    loaded++;
                }
            }

        } catch (IOException e) {
            System.out.println("[ОШИБКА]: при чтении файла возникла ошибка: " + e.getMessage());
            return res;
        }

        // Преобразуем List в массив Car[]
        Car[] carArray = carList.toArray(new Car[0]);

        // Используем StreamFill для заполнения коллекции
        StreamFill.fill(res, carArray);

        System.out.println("Загружено " + res.size() + " объектов из файла: " + file.getName());

        return res;
    }

    private Car parseLineToCar(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length >= 3) {
                return new Car.Builder()
                        .setModel(parts[0].trim())
                        .setYear(Integer.parseInt(parts[1].trim()))
                        .setPower(Integer.parseInt(parts[2].trim()))
                        .build();
            } else {
                System.out.println("[ОШИБКА]: неверный формат строки (меньше чем 3 элемента): " + line);
            }
        } catch (NumberFormatException e) {
            System.out.println("ERROR: bad number: " + line);
        }
        return null;
    }
}
