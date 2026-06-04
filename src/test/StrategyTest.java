package test;
import collection.CustomArrayList;
import comparator.PowerComparator;
import strategy.SortContext;
import strategy.TimSort;
import model.Car;

import java.util.ArrayList;
import java.util.List;

public class StrategyTest {
    public static void main(String[] args) {
        Car bmw = new Car.Builder()
                .setModel("BMW")
                .setPower(200)
                .setYear(2020)
                .build();
        Car honda = new Car.Builder()
                .setModel("Honda")
                .setPower(140)
                .setYear(2016)
                .build();
        Car toyota = new Car.Builder()
                .setModel("Toyota")
                .setPower(350)
                .setYear(1998)
                .build();
        CustomArrayList<Car> cars = new CustomArrayList<>();
        cars.add(bmw);
        cars.add(honda);
        cars.add(toyota);
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        SortContext context = new SortContext();
        context.executeSort(cars, new PowerComparator());
    }
}
