package strategy;

import collection.CustomArrayList;
import model.Car;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TimSort implements SortStrategy{
    @Override
    public void sort(CustomArrayList<Car> cars, Comparator<Car> comparator){}

    @Override
    public void sortWithRule(CustomArrayList<Car> cars, Comparator<Car> comparator, Function<Car, Integer> number) {}
}
