package validation;

public class CarValidator {
    public static boolean validate(String model, int year, int power) {
        if (model == null || model.isBlank()) {
            System.out.println("Неверная модель");
            return false;
        }
        if (year < 1886 || year > 2025) {
            System.out.println("Неверный год");
            return false;
        }
        if (power < 1 || power > 1000) {
            System.out.println("Неверная мощность");
            return false;
        }
        return true;
    }
}
