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
        // TODO: заполнение вручную
        CustomArrayList<Car> res = new CustomArrayList<>();

        if (scanner == null) {
            System.err.println("ERROR: no scanner provided");
            return res;
        }

        if (count <= 0) {
            System.err.println("ERROR: number of cars must be at least 1");
            return res;
        }

        List<Car> carList = new ArrayList<>();
        System.out.println("Enter info about " + count + " cars: ");
        System.out.println("Tip: Mercedes-Benz,2025,500");

        for (int i = 0; i < count; i++) {
            System.out.print("Car number " + i + ": ");
            String input = scanner.nextLine();

            Car temp = parseInputToCar(input);

            if (temp != null) {
                carList.add(temp);
            } else {
                System.err.println("ERROR: wrong string input");
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
            System.err.println("ERROR: 3 items needed(model, year, power)");
            return null;
        }

        try {
            String model = parts[0].trim();
            int year = Integer.parseInt(parts[1].trim());
            int power = Integer.parseInt(parts[2].trim());

            // Дополнительная валидация
            if (year < 1886 || year > 2025) {
                System.err.println("Ошибка: год должен быть между 1886 и 2025");
                return null;
            }

            if (power < 0 || power > 2000) {
                System.err.println("Ошибка: мощность должна быть между 0 и 2000 л.с.");
                return null;
            }

            return new Car.Builder()
                    .setModel(model)
                    .setYear(year)
                    .setPower(power)
                    .build();

        } catch (NumberFormatException e) {
            System.err.println("Ошибка: год и мощность должны быть числами");
            return null;
        }
    }
}
