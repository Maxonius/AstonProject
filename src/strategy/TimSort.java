package strategy;

import model.Car;
import collection.CustomArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

public class TimSort implements SortStrategy{
    private static final int RUN = 32;

    private void insertionSort(List<Car> cars, int left, int right, Comparator<Car> comparator){
        for (int i = left + 1; i <= right ; i++) {
            int j = i - 1;
            Car temp = cars.get(i);

            while (j >= left && comparator.compare(cars.get(j), temp) > 0){
                cars.set(j + 1, cars.get(j));
                j--;
            }
            cars.set(j + 1, temp);
        }
    }


    @Override
    public void sort(List<Car> cars, Comparator<Car> comparator){
        int n = cars.size();

        for (int i = 0; i < n; i+=RUN) {
            insertionSort(cars, i, Math.min(i + RUN - 1, n - 1), comparator);
        }
    }

    @Override
    public void sortWithRule(List<Car> cars, Comparator<Car> comparator, Function<Car, Integer> number) {}
}
