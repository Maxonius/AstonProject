package test;

import model.Car;

public class CarBuilderTest {
    public static void main(String[] args) {
        Car carBMW = new Car.Builder()
                .setModel("BMW")
                .setYear(2007)
                .setPower(272)
                .build();
        System.out.println(carBMW);

        try {
            Car carLada = new Car.Builder()
                    .setModel("Lada")
                    .setYear(2000)
                    .setPower(-1)
                    .build();
            System.out.println(carLada);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
