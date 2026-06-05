package test;
import collection.CustomArrayList;
import comparator.ModelComparator;
import comparator.PowerComparator;
import comparator.YearComparator;
import strategy.SortContext;
import model.Car;


public class StrategyTest {
    public static void main(String[] args) {
        CustomArrayList<Car> cars = new CustomArrayList<>();
        String[] models = {
                "BMW",
                "Audi",
                "Toyota",
                "Mazda",
                "Honda",
                "Jaguar",
                "Mercedes",
                "Lada",
                "Volkswagen",
                "Ford"
        };
        for (int i = 0; i < models.length; i++) {

            cars.add(
                    new Car.Builder()
                            .setModel(models[i])
                            .setYear(2000 + i)
                            .setPower(100 + i)
                            .build()
            );
        }
//        for (int i = 0; i < 50; i++) {
//            cars.add(
//                    new Car.Builder()
//                            .setModel("Car" + i)
//                            .setYear(1950 + i)
//                            .setPower((int)(Math.random() * 500))
//                            .build()
//            );
//        }
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        SortContext context = new SortContext();
        context.executeSort(cars, new PowerComparator());
        System.out.println("------------------------------------------------------------------\nPOWER_COMPARATOR");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        context.executeSort(cars, new YearComparator());
        System.out.println("------------------------------------------------------------------\nYEAR_COMPARATOR");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
        context.executeSort(cars, new ModelComparator());
        System.out.println("------------------------------------------------------------------\nMODEL_COMPARATOR");
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i));
        }
    }
}
