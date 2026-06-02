package validation;

public class CarValidator {
    public static void validate(String model, int year, int power) {
        if (model == null || model.isBlank()) {
            throw new IllegalArgumentException("Model is invalid");
        }
        if (year < 1900 || year > 2030) {
            throw new IllegalArgumentException("Year is invalid");
        }
        if (power < 1 || power > 1000) {
            throw new IllegalArgumentException("Power is invalid");
        }
    }
}
