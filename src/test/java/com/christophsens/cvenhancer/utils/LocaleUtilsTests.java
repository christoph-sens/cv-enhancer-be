package com.christophsens.cvenhancer.utils;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocaleUtilsTests {
    
    private static final String ENGLISH_ISO_LANGUAGE_CODE = "en";
    private static final String ENGLISH_ISO_COUNTRY_CODE = "GB";
    
    private static final String UNKNOWN_ISO_LANGUAGE_AND_COUNTRY_CODE = "zz";
   
    @Test
    public void testLocales(){
        Assertions.assertTrue(IsoUtils.isValidIsoLocale(ENGLISH_ISO_LANGUAGE_CODE, ENGLISH_ISO_COUNTRY_CODE));
        Assertions.assertFalse(IsoUtils.isValidIsoLocale(ENGLISH_ISO_LANGUAGE_CODE, UNKNOWN_ISO_LANGUAGE_AND_COUNTRY_CODE));
        Assertions.assertFalse(IsoUtils.isValidIsoLocale(UNKNOWN_ISO_LANGUAGE_AND_COUNTRY_CODE, ENGLISH_ISO_COUNTRY_CODE));
        Assertions.assertFalse(IsoUtils.isValidIsoLocale(UNKNOWN_ISO_LANGUAGE_AND_COUNTRY_CODE, UNKNOWN_ISO_LANGUAGE_AND_COUNTRY_CODE));
    } 
}