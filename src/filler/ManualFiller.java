package filler;

import collection.CustomArrayList;
import model.Car;

import java.util.Scanner;

public class ManualFiller implements DataFiller{
    private final Scanner scanner;

    public ManualFiller(Scanner scanner){
        this.scanner = scanner;
    }

    @Override
    public CustomArrayList<Car> fill(int count){
        // TODO: заполнение вручную
        return new CustomArrayList<>();
    }

}
