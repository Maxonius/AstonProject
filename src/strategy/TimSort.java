package strategy;

import model.Car;
import collection.CustomArrayList;
import java.util.Comparator;
import java.util.function.Function;

public class TimSort implements SortStrategy{
    private static final int RUN = 32;

    private void insertionSort(CustomArrayList<Car> cars, int left, int right, Comparator<Car> comparator){
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

    private void merge(CustomArrayList<Car> cars, int left, int mid, int right, Comparator<Car> comparator){
        CustomArrayList<Car> leftPart = new CustomArrayList<>();
        CustomArrayList<Car> rightPart = new CustomArrayList<>();

        for (int i = left; i <= mid; i++) {
            leftPart.add(cars.get(i));
        }
        for (int i = mid + 1; i <= right; i++) {
            rightPart.add(cars.get(i));
        }
        int i = 0;
        int j = 0;
        int k = left;

        while (i < leftPart.size() && j < rightPart.size()){
            if(comparator.compare(leftPart.get(i), rightPart.get(j)) <= 0){
                cars.set(k, leftPart.get(i));
                i++;
            } else {
                cars.set(k, rightPart.get(j));
                j++;
            }
            k++;
        }
        while (i < leftPart.size()){
            cars.set(k, leftPart.get(i));
            i++;
            k++;
        }
        while (j < rightPart.size()){
            cars.set(k, rightPart.get(j));
            j++;
            k++;
        }
    }

    @Override
    public void sort(CustomArrayList<Car> cars, Comparator<Car> comparator) {
        int n = cars.size();

        for (int i = 0; i < n; i+=RUN) {
            insertionSort(cars, i, Math.min(i + RUN - 1, n - 1), comparator);
        }
        for (int size = RUN; size < n; size *= 2) {
            for (int left = 0; left < n; left += 2 * size) {
                int mid = left + size - 1;
                if (mid >= n - 1){
                    continue;
                }
                int right = Math.min(left + 2 * size - 1, n - 1);
                merge(cars, left, mid, right, comparator);
            }
        }
    }

    @Override
    public void sortWithRule(CustomArrayList<Car> cars, Comparator<Car> comparator, Function<Car, Integer> number) {

    }
}
