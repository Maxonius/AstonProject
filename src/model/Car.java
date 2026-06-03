package model;

import validation.CarValidator;
import java.util.Objects;

public class Car {
    private final String model;
    private final int year;
    private final int power;

    private Car(String model, int year, int power) {
        this.model = model;
        this.year = year;
        this.power = power;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return year == car.year &&
               power == car.power &&
               Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, year, power);
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", power=" + power +
                '}';
    }

    public static class Builder {
        private String model;
        private int year;
        private int power;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setYear(int year) {
            this.year = year;
            return this;
        }

        public Builder setPower(int power) {
            this.power = power;
            return this;
        }

        public Car build() {
            CarValidator.validate(model, year, power);
            return new Car(model, year, power);
        }
    }
}
