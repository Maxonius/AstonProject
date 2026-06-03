package test;

import collection.CustomArrayList;
import model.Car;
import multithreading.MultiThreadCounter;

public class MultiThreadCounterTest {

    public static void main(String[] args) {
        testOneThread();
        testMultipleThreads();
        testEmptyList();
    }

    static void check(int expected, int actual, String name) {
        if (expected == actual) {
            System.out.println("[OK] " + name);
        } else {
            System.out.println("[ОШИБКА] " + name + ": ждали " + expected + ", получили " + actual);
        }
    }

    static void testOneThread() {
        CustomArrayList<Car> list = new CustomArrayList<>();
        Car target = new Car.Builder().setModel("BMW").setYear(2020).setPower(250).build();

        list.add(target);
        list.add(new Car.Builder().setModel("Audi").setYear(2021).setPower(200).build());
        list.add(target);
        list.add(new Car.Builder().setModel("Audi").setYear(2021).setPower(200).build());
        list.add(target);

        int result = MultiThreadCounter.countOccurrences(list, target, 1);
        check(3, result, "Один поток, 3 вхождения");
    }

    static void testMultipleThreads() {
        CustomArrayList<Car> list = new CustomArrayList<>();
        Car target = new Car.Builder().setModel("Lada").setYear(2010).setPower(90).build();

        for (int i = 0; i < 10; i++) {
            if (i == 0 || i == 5 || i == 9) {
                list.add(target);
            } else {
                list.add(new Car.Builder().setModel("Toyota").setYear(2015).setPower(150).build());
            }
        }

        int result = MultiThreadCounter.countOccurrences(list, target, 4);
        check(3, result, "4 потока, 3 вхождения");
    }

    static void testEmptyList() {
        CustomArrayList<Car> list = new CustomArrayList<>();
        Car target = new Car.Builder().setModel("Opel").setYear(2014).setPower(110).build();
        int result = MultiThreadCounter.countOccurrences(list, target, 2);
        check(0, result, "Пустой список");
    }
}
