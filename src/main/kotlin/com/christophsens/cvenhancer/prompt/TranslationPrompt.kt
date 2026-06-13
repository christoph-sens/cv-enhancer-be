package com.christophsens.cvenhancer.prompt

import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.PromptTemplate

object TranslationPrompt {

    private val TEMPLATE = """
        Translate the text according to iso693 "{languageCode}_{countryCode}"
        This is the cv: "{cv}"
    """.trimIndent()

    fun getPrompt(cv: String, languageCode: String, countryCode: String): Prompt {
        val promptTemplate = PromptTemplate(TEMPLATE)
        promptTemplate.add("cv", cv)
        promptTemplate.add("languageCode", languageCode.lowercase())
        promptTemplate.add("countryCode", countryCode.uppercase())
        return promptTemplate.create()
    }
}