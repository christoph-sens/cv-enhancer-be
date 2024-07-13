package com.christophsens.cvenhancer.utils;

import java.util.Locale;
import java.util.Set;

public final class IsoUtils {
    private static final Set<String> ISO_LANGUAGES = Set.of(Locale.getISOLanguages());
    private static final Set<String> ISO_COUNTRIES = Set.of(Locale.getISOCountries());

    private IsoUtils() {
    }
    private static boolean isValidISOLanguage(String s) {
        return ISO_LANGUAGES.contains(s);
    }
    private static boolean isValidISOCountry(String s) {
        return ISO_COUNTRIES.contains(s);
    }
    public static boolean isValidIsoLocale(String languageCode, String countryCode) {
        return isValidISOLanguage(languageCode) && isValidISOCountry(countryCode); 
    }
}