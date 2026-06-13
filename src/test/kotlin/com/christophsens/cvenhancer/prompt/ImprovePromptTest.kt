package com.christophsens.cvenhancer.prompt

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ImprovePromptTest {

    @Test
    fun testGetPrompt() {
        val cv = "Sample CV content"
        val contents = ImprovePrompt.getPrompt(cv).contents
        assertEquals("Improve this cv: \"${cv}\".\nYour response must be in the language of the CV.", contents)
    }
}