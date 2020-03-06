package pl.kazet.stockmarket.utils;

public class Validator {
    public static boolean containParameters(Object... parameters) {
        for (Object parameter : parameters) {
            if (parameter == null) {
                return false;
            }
        }
        return true;
    }
}
