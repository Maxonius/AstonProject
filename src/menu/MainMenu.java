package menu;

import collection.CustomArrayList;
import comparator.ModelComparator;
import comparator.PowerComparator;
import comparator.YearComparator;
import model.Car;
import strategy.SortContext;

import java.util.Comparator;
import java.util.Scanner;
import java.util.function.Function;


public class MainMenu {
    private final Scanner scanner;
    private  final SortContext sortContext;

    private CustomArrayList<Car> carCollection;
    private Comparator<Car> selectedComparator;
    private int collectionSize;

    public MainMenu() {
        this.scanner = new Scanner(System.in);
        this.sortContext = new SortContext();
        this.collectionSize = 10;
    }

    public void run() {
        System.out.println("=".repeat(40));
        System.out.println("          Sorting Application");
        System.out.println("TimSort - сортировка объектов Автомобиль");
        System.out.println("=".repeat(40));

        while (true){
            printMenu();
            int choice = readInt("Выберите действие: ", 0, 8);

            switch (choice){
                case 1 -> fillCollection();
                case 2 -> setCollectionSize();
                case 3 -> selectSortField();
                case 4 -> executeBasicSort();
                case 5 -> executeRuleSort();
                case 6 -> saveToFile();
                case 7 -> searchOccurrences();
                case 8 -> printCollection();
                case 0 -> {
                    System.out.println("До свидания.");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Некорректный выбор. Попробуйте снова.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n" + "-".repeat(40));
        System.out.println("ГЛАВНОЕ МЕНЮ");
        System.out.println("-".repeat(40));
        System.out.println("Алгоритм: TimSort");
        System.out.println("Размер коллекции: " + collectionSize);
        System.out.println("Коллекция " + (carCollection != null ?
                "заполнена (" + carCollection.size() + " шт.)" : "не заполнена"));
        System.out.println("Поле сортировки: " + (selectedComparator != null ?
                getComparatorName() : "не выбрано"));
        System.out.println("-".repeat(40));
        System.out.println("1. Заполнить коллекцию");
        System.out.println("2. Указать размер коллекции");
        System.out.println("3. Выбрать поле сортировки");
        System.out.println("4. Выполнить сортировку");
        System.out.println("5. Сортировка (четное/нечетное)");
        System.out.println("6. Сохранить в файл");
        System.out.println("7. Поиск вхождений");
        System.out.println("8. Показать коллекцию");
        System.out.println("0. Выход");
        System.out.println("-".repeat(40));
    }

    private void fillCollection() {
        System.out.println("\nСпособы заполнения: ");
        System.out.println("1. Из файла");
        System.out.println("2. Случайные данные");
        System.out.println("3. Вручную");
        System.out.println("0. Отмена");

        int choice = readInt("Выберите способ: ", 0, 3);

        //TODO: выбор филлера

        System.out.println("Заполнение...");
        long start = System.currentTimeMillis();
        // TODO: заполнение
        long end = System.currentTimeMillis();
        if (carCollection == null || carCollection.isEmpty()) {
            System.out.println("Не удалось заполнить коллекцию.");
        }
        else {
            System.out.println("Заполнено: " + carCollection.size() + " элементов за " + (end-start) + "мс");
        }
    }

    private void setCollectionSize() {
        int newSize = readInt("Размер коллекции (1-10000): ", 1, 10_000);
        collectionSize = newSize;
        carCollection = null;
        System.out.println("Установлен размер: " + collectionSize);
    }

    private void selectSortField() {
        System.out.println("\nПоля для сортировки");
        System.out.println("1. Модель");
        System.out.println("2. Год производства");
        System.out.println("3. Мощность");

        int choice = readInt("Выберите поле: ",1,3);

        selectedComparator = switch (choice) {
            case 1 -> new ModelComparator();
            case 2 -> new YearComparator();
            case 3 -> new PowerComparator();
            default -> null;
        };

        System.out.println("Выбрано поле: " + getComparatorName());
    }

    private void executeBasicSort() {
        if (!validateBeforeSort()) return;

        sortContext.executeSort(carCollection, selectedComparator);

        System.out.println("\nРезультат сортировки:");
        printCars(carCollection);
    }

    private void executeRuleSort() {
        if (!validateBeforeSort()) return;

        if (!(selectedComparator instanceof PowerComparator) &&
        !(selectedComparator instanceof  YearComparator)){
            System.out.println("Выберите числовое поле (мощность или год).");
            return;
        }

        Function<Car, Integer> number;
        if(selectedComparator instanceof PowerComparator){
            number = Car::getPower;
        }
        else{
            number = Car::getYear;
        }

        sortContext.executeSortWithRule(carCollection, selectedComparator, number);

        System.out.println("\nРезультат сортировки четных: ");
        printCars(carCollection);
    }

    private void saveToFile() {
        if (carCollection == null || carCollection.isEmpty()) {
            System.out.println("Коллекция пуста. Сначала заполните.");
            return;
        }

        //TODO: заполнение файла
    }

    private void searchOccurrences() {
        if (carCollection == null || carCollection.isEmpty()) {
            System.out.println("Коллекция пуста. Сначала заполните.");
            return;
        }

        //TODO: многопоточка
    }

    private void printCollection() {
        if (carCollection == null || carCollection.isEmpty()) {
            System.out.println("Коллекция пуста. Сначала заполните.");
            return;
        }
        System.out.println("\nКоллекция (" + carCollection.size() + " элементов):");
        printCars(carCollection);
    }

    private void printCars(CustomArrayList<Car> carCollection) {
        for (int i =0; i < carCollection.size(); i++) {
            System.out.println("[" + (i+1) +"] " +carCollection.get(i).toString());
        }
    }


    private boolean validateBeforeSort() {
        if (carCollection == null || carCollection.isEmpty()){
            System.out.println("Ошибка: коллекция пуста.");
            return false;
        }
        if(selectedComparator == null){
            System.out.println("Ошибка: не выбрано поле сортировки");
            return false;
        }
        return true;
    }


    private String getComparatorName() {
        if (selectedComparator instanceof PowerComparator) return "Мощность";
        if (selectedComparator instanceof ModelComparator) return "Модель";
        if (selectedComparator instanceof YearComparator) return "Год производства";
        return "Не задан";
    }

    private int readInt(String text, int min, int max){
        while (true){
            System.out.print(text);
            String input = scanner.nextLine().trim();
            try{
                int value = Integer.parseInt(input);
                if (value >= min && value <= max){
                    return value;
                }
                System.out.println("Ошибка: введите число от " + min + " до " + max);
            }
            catch (NumberFormatException e){
                System.out.println("Ошибка: введите целое число");
            }
        }
    }
}
