package test;

import collection.CustomArrayList;
import io.RWsystem;
import model.Car;
import io.ConvertToCar;
import io.StreamFill;

import java.io.IOException;

public class RWSystemTest {
    public static void main(String[] args) {
        CustomArrayList<Car> carList = new CustomArrayList<>();

        Car KIA = new Car.Builder()
                .setModel("KIA Rio")
                .setYear(2014)
                .setPower(123)
                .build();

        Car MB = new Car.Builder()
                .setModel("Mercedes E-class")
                .setYear(2021)
                .setPower(500)
                .build();

        carList.add(KIA);
        carList.add(MB);

        try {
            RWsystem.appendToCSV(carList, "test.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        CustomArrayList<Car> cars = new CustomArrayList<>();
        ConvertToCar converter = new ConvertToCar();

        try {
            RWsystem.loadFromCSV(cars, "test.csv", converter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < cars.size(); i++) {
            System.out.println(i + ": " + cars.get(i));
        }

        CustomArrayList<Car> newList = new CustomArrayList<>();
        Car[] newCarList = new Car[3];
        newCarList[0] = new Car.Builder().setModel("Audi").setYear(2021).setPower(350).build();
        newCarList[1] = new Car.Builder().setModel("BMW").setYear(2021).setPower(360).build();
        newCarList[2] = new Car.Builder().setModel("Changan").setYear(2026).setPower(250).build();

        StreamFill.fill(newList, newCarList);
        try {
            RWsystem.appendToCSV(newList, "test2.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
