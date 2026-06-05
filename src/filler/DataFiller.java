package filler;

import collection.CustomArrayList;
import model.Car;

/**
 * Интрефейс для заполнения коллекций автомобилей.
 * Реализует FileFiller, RandomFiller, ManualFiller.
 */
public interface DataFiller {

    /**
     * Заполняет коллекицию. Использует Stream
     * @param count колличество элементов
     * @return заполненная коллекция (может содержать меньше элементов если данные кончаться, но не null)
     */
    CustomArrayList<Car> fill (int count);
}
