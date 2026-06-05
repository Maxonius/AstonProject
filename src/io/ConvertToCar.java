package io;

import model.Car;

public class ConvertToCar implements IConvert {
    @Override
    public Car convert(String line) {
        if (line == null || line.isEmpty()) {
            return null;
        } else {
            String[] parts = line.split(",");

            Car result = new Car.Builder()
                    .setModel(parts[0].trim())
                    .setYear(Integer.parseInt(parts[1].trim()))
                    .setPower(Integer.parseInt(parts[2].trim()))
                    .build();

            return result;
        }
    }
}
