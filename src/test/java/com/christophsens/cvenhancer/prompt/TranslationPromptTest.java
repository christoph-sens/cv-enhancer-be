package com.christophsens.cvenhancer.prompt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;

public class TranslationPromptTest {

    @Test
    void testTranslationPrompt() {
        String expectedResult = """
        Translate the text according to iso693 "en_GB"
        This is the cv: "cv"
        """;
        Prompt prompt = TranslationPrompt.getPrompt("cv", "en", "GB");
        String result = prompt.getContents();
        Assertions.assertEquals(expectedResult, result);
    }
}