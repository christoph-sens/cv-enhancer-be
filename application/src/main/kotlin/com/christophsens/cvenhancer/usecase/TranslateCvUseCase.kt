package com.christophsens.cvenhancer.usecase

import com.christophsens.cvenhancer.application.port.`in`.TranslateCvPort
import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort
import com.christophsens.cvenhancer.model.valueobject.LocaleValueObject

class TranslateCvUseCase (
    private val openAiChatPort: OpenAiChatPort
) : TranslateCvPort {
    override fun translate(
        cvContent: String,
        locale: LocaleValueObject
    ): String {
        return openAiChatPort.call(buildTranslationPrompt(cvContent, locale))
    }
    private fun buildTranslationPrompt(cvContent: String,
    locale: LocaleValueObject): String {
        return """
        Translate the text according to iso693 "{${locale.getLanguageCode()}}_{${locale.getCountryCode()}}"
        This is the cv: "{$cvContent}"
    """.trimIndent()

    }
}