package strategy;

import collection.CustomArrayList;
import model.Car;

import java.util.Comparator;
import java.util.function.Function;

/**
 * Интерфейс стратегии сортировки.
 * Реализуется алгоритмом TimSort.
 */
public interface SortStrategy {
    /**
     * Базовая сортировка.
     *
     * @param cars список автомобилей.
     * @param comparator компаратор, определяющий порядок сортировки.
     */
    void sort(CustomArrayList<Car> cars, Comparator<Car> comparator);

    /**
     * Сортировка с правилом четное/нечетное.
     * Объекты с четным значением числового поля сортировки сортируются в натуральном порядке,
     * а с нечетным - остаются на месте.
     *
     * @param cars список автомобилей.
     * @param comparator компаратор, определяющий порядок сортировки четных элементов.
     * @param number функция, извлекающая числовое поле (power или year).
     */
    void sortWithRule(CustomArrayList<Car> cars, Comparator<Car> comparator, Function<Car, Integer> number);
}
