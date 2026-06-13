package com.christophsens.cvenhancer.prompt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class TranslationPromptTest {

    @Test
    fun testGetPrompt() {
        val cv = "Sample CV content"
        val languageCode = "en"
        val countryCode = "US"
        val contents = TranslationPrompt.getPrompt(cv, languageCode, countryCode).contents

        assertEquals("Translate the text according to iso693 \"en_US\"\nThis is the cv: \"${cv}\"", contents)
    }
}