package org.example.Client.View;


import java.util.Locale;
import java.util.ResourceBundle;

public class Language {
    private static Language instance;
    private ResourceBundle resourceBundle;
    private Locale currentLocale;

    private Language() {
        setLocale(Locale.ENGLISH); // Default language
    }
    public static synchronized Language getInstance() {
        if (instance == null) {
            instance = new Language();
        }
        return instance;
    }

    public void setLocale(Locale locale) {
        this.currentLocale = locale;
        resourceBundle = ResourceBundle.getBundle("translations", locale);
    }


    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }
    public void switchLanguage(String languageCode) {
        switch (languageCode) {
            case "en":
                setLocale(Locale.ENGLISH);
                break;
            case "fr":
                setLocale(Locale.FRENCH);
                break;
            case "it":
                setLocale(Locale.ITALIAN);
                break;
            default:
                setLocale(Locale.ENGLISH);
        }
    }

}
