package com.christophsens.cvenhancer.model.valueobject

import java.util.Locale

/**
 * Value Object representing a locale with ISO language and country codes.
 */
class LocaleValueObject(
    private val languageCode: String,
    private val countryCode: String
) {
    init {
        require(isValidIsoLanguage(languageCode)) { "Invalid ISO language code: $languageCode" }
        require(isValidIsoCountry(countryCode)) { "Invalid ISO country code: $countryCode" }
    }

    companion object {
        private val isoLanguages: Set<String> = Locale.getISOLanguages().toSet()
        private val isoCountries: Set<String> = Locale.getISOCountries().toSet()

        /*@JvmStatic
        fun isValidIsoLanguage(value: String): Boolean {
            return isoLanguages.contains(value)
        }

        @JvmStatic
        fun isValidIsoCountry(value: String): Boolean {
            return isoCountries.contains(value)
        }*/
    }

    private fun isValidIsoLanguage(value: String): Boolean = isoLanguages.contains(value)
    private fun isValidIsoCountry(value: String): Boolean = isoCountries.contains(value)
}