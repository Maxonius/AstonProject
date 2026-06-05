package io;

import collection.CustomArrayList;
import model.Car;

import java.util.Arrays;
import java.util.stream.*;

public class StreamFill {
    public static void fill(CustomArrayList<Car> list, Car[] carList) {
        if (list == null || carList == null) {
            return;
        } else {
            Arrays.stream(carList)
                    .filter(Car -> Car != null)
                    .forEach(list::add);
        }
    }
}
