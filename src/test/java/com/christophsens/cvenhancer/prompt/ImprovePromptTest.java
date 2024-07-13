package com.christophsens.cvenhancer.prompt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;

public class ImprovePromptTest {

    @Test
    void testTranslationPrompt() {
        String expectedResult = """
            Improve this cv: "cv".
            Your response must be in the language of the CV.
            """;
        Prompt prompt = ImprovePrompt.getPrompt("cv");
        String result = prompt.getContents();
        Assertions.assertEquals(expectedResult, result);
    }
}