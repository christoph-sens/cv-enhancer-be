package com.christophsens.cvenhancer.prompt

import com.christophsens.cvenhancer.dto.CvVacancyDto
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class CompareVacancyPromptTest {

    @Test
    fun testGetPrompt() {
        val dto = CvVacancyDto("cv content", "vacancy description")
        val contents = CompareVacancyPrompt.getPrompt(dto).contents

        val expectedInstruction = """This is the cv: "cv content". This is the vacancy:"vacancy description".
Give feedback how to improve the cv according to the vacancy.
Your response must be in the language of the CV.""".trimIndent()
        assertEquals(expectedInstruction, contents)
    }

}