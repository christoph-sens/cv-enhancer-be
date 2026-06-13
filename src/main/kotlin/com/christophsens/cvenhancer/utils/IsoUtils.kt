package com.christophsens.cvenhancer.utils

import java.util.Locale

object IsoUtils {

    private val isoLanguages: Set<String> = Locale.getISOLanguages().toSet()
    private val isoCountries: Set<String> = Locale.getISOCountries().toSet()

    private fun isValidIsoLanguage(value: String): Boolean {
        return isoLanguages.contains(value)
    }

    private fun isValidIsoCountry(value: String): Boolean {
        return isoCountries.contains(value)
    }

    fun isValidIsoLocale(languageCode: String, countryCode: String): Boolean {
        return isValidIsoLanguage(languageCode) && isValidIsoCountry(countryCode)
    }
}