package filler;

import collection.CustomArrayList;
import model.Car;
import io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManualFiller implements DataFiller {
    private final Scanner scanner;

    public ManualFiller(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public CustomArrayList<Car> fill(int count) {
        CustomArrayList<Car> res = new CustomArrayList<>();

        if (scanner == null) {
            System.out.println("[ОШИБКА]: не указан объект для ввода");
            return res;
        }

        if (count <= 0) {
            System.out.println("[ОШИБКА]: количество машин должно быть больше нуля");
            return res;
        }

        List<Car> carList = new ArrayList<>();
        System.out.println("Введите информацию о " + count + " машинах: ");
        System.out.println("Пример: Mercedes-Benz,2025,500");

        for (int i = 0; i < count; i++) {
            System.out.print("Машина номер " + i + ": ");
            String input = scanner.nextLine();

            if (input == null || input.trim().isEmpty()) {
                System.out.println("[ВНИМАНИЕ]: пустая строка");
                i--;
                continue;
            }

            Car temp = parseInputToCar(input);

            if (temp != null) {
                carList.add(temp);
            } else {
                System.out.println("[ОШИБКА]: неверная строка");
                i--;
            }
        }
        StreamFill.fill(res, carList.toArray(new Car[0]));

        return res;
    }

    private Car parseInputToCar(String input) {
        if (input == null || input.trim().isEmpty()) {
            return null;
        }

        String[] parts = input.split(",");
        if (parts.length < 3) {
            System.out.println("[ОШИБКА]: необходимо указать 3 элемента(модель, год и мощность)");
            return null;
        }

        try {
            String model = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim());
            int power = Integer.parseInt(parts[2].trim());

            // Дополнительная валидация
            if (year < 1886 || year > 2025) {
                System.out.println("[ОШИБКА]: год должен быть между 1886 и 2025");
                return null;
            }

            if (power < 0 || power > 2000) {
                System.out.println("[ОШИБКА]: мощность должна быть между 0 и 2000 л.с.");
                return null;
            }

            return new Car.Builder()
                    .setModel(model)
                    .setYear(year)
                    .setPower(power)
                    .build();

        } catch (NumberFormatException e) {
            System.out.println("[ОШИБКА]: год и мощность должны быть числами");
            return null;
        }
    }
}
