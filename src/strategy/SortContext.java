package strategy;

import collection.CustomArrayList;
import model.Car;

import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/**
 * Контекст для паттерна Стратегия.
 * Содержит единственную TimSort.
 */
public class SortContext {

    private final SortStrategy strategy;

    public  SortContext(){
        this.strategy = new TimSort();
    }

    /**
     * Выполнить базовую сортировку.
     *
     * @param cars список автомобилей.
     * @param comparator компаратор, определяющий порядок сортировки.
     */
    public void executeSort(CustomArrayList<Car> cars, Comparator<Car> comparator){
        System.out.println("Сортировка: TimSort");
        long startTime = System.currentTimeMillis();
        strategy.sort(cars, comparator);
        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка завершена за " + (endTime - startTime) + " мс");
    }

    /**
     * Выполнить сортировку с правилом (четное/нечетное)
     *
     * @param cars список автомобилей.
     * @param comparator компаратор, определяющий порядок сортировки четных элементов.
     * @param number функция, извлекающая числовое поле (power или year).
     */
    public void executeSortWithRule(CustomArrayList<Car> cars, Comparator<Car> comparator, Function<Car, Integer> number){
        System.out.println("Сортировка (четные/нечетные): TimSort");
        long startTime = System.currentTimeMillis();
        strategy.sortWithRule(cars, comparator, number);
        long endTime = System.currentTimeMillis();
        System.out.println("Сортировка завершена за " + (endTime - startTime) + " мс");
    }
}
