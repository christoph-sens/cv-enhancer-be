package com.christophsens.cvenhancer.prompt

import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.chat.prompt.PromptTemplate

object ImprovePrompt {

    private val TEMPLATE = """
        Improve this cv: "{cv}".
        Your response must be in the language of the CV.
    """.trimIndent()

    fun getPrompt(cv: String): Prompt {
        val promptTemplate = PromptTemplate(TEMPLATE)
        promptTemplate.add("cv", cv)
        return promptTemplate.create()
    }
}