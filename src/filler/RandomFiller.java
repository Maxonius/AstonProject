package filler;

import collection.CustomArrayList;
import model.Car;
import io.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RandomFiller implements DataFiller {
    private final Random rand;

    public RandomFiller() {
        this.rand = new Random();
    }

    private final String[] carNames = {"KIA Rio", "KIA K5", "KIA C'eed", "KIA Sportage",
            "Mercedes-Benz E-calss", "Mercedes-Benz S-calss", "Lexus RX", "Audi A5",
            "LADA Granta", "Hyundai Elantra", "Hyundai Solaris", "Chery Tiggo 7", "Chery Tiggo 4"};

    private Car generateRandomCar() {
        String carModel = carNames[rand.nextInt(carNames.length)];
        int year = 2000 + rand.nextInt(8);
        int power = 100 + rand.nextInt(401);

        return new Car.Builder()
                .setModel(carModel)
                .setYear(year)
                .setPower(power)
                .build();
    }

    @Override
    public CustomArrayList<Car> fill(int count) {
        CustomArrayList<Car> res = new CustomArrayList<>();

        if (count <= 0) {
            System.out.println("[ОШИБКА]: число машин должно быть больше нуля");
            return res;
        }

        List<Car> carList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            carList.add(generateRandomCar());
        }

        Car[] carArray = carList.toArray(new Car[0]);
        StreamFill.fill(res, carArray);

        return res;
    }
}
