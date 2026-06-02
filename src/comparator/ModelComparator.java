package comparator;

import model.Car;

import java.util.Comparator;

public class ModelComparator implements Comparator<Car> {
    @Override
    public int compare(Car o1, Car o2) {
        return String.CASE_INSENSITIVE_ORDER.compare(o1.getModel(), o2.getModel());
    }
}
