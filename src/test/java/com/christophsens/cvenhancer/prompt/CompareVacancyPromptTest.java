package com.christophsens.cvenhancer.prompt;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;

public class CompareVacancyPromptTest {

    @Test
    void testCompareVacancyPrompt() {
        String expectedResult = """
            This is the cv: "cv". This is the vacancy:"vacancy".
            Give feedback how to improve the cv according to the vacancy.
            Your response must be in the language of the CV.
            """;
        Prompt prompt = CompareVacancyPrompt.getPrompt(new CvVacancyDto("cv", "vacancy"));
        String result = prompt.getContents();
        Assertions.assertEquals(expectedResult, result);
    }
}