package ua.nure.usik.practice4;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public final class Translator {

    private Translator() {
        throw new IllegalStateException("Utility class");
    }

    public static String translate(String text, String locale) {
        ResourceBundle defRb = ResourceBundle.getBundle("resources", new Locale(locale));
        try {
            return defRb.getString(text);
        } catch (MissingResourceException ex) {
            return "No such values";
        }
    }
}
