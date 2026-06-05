package filler;

import collection.CustomArrayList;
import model.Car;

public class RandomFiller implements  DataFiller{

    public RandomFiller(){}

    @Override
    public CustomArrayList<Car> fill(int count){
        // TODO: заполнение рандомными данными
        return new CustomArrayList<>();
    }
}
