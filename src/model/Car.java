package model;

public class Car {
    private final String model;
    private final int year;
    private final int power;

    public Car(String model, int year, int power){
        this.model = model;
        this.year = year;
        this.power = power;
    }

    public String getModel(){
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getPower() {
        return power;
    }

    @Override
    public String toString() {
        return "Car{" +
                "model='" + model + '\'' +
                ", year=" + year +
                ", power=" + power +
                '}';
    }
}
