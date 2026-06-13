package com.christophsens.cvenhancer.usecase

import com.christophsens.cvenhancer.application.port.`in`.ImproveCvPort
import com.christophsens.cvenhancer.application.port.out.OpenAiChatPort

class ImproveCvUseCase(
    private val openAiChatPort: OpenAiChatPort
) : ImproveCvPort {
    override fun improve(cvContent: String): String {
        val prompt = buildImproveCvPrompt(cvContent)
        val response = openAiChatPort.call(prompt)
        return response
    }

    private fun buildImproveCvPrompt(cvContent: String): String {
        return """
            This is the CV: "$cvContent".
            Give feedback on how to improve it.
            Your response must be in the language of the CV.
        """.trimIndent()
    }
}