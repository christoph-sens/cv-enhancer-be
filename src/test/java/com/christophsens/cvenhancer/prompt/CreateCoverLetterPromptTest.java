package com.christophsens.cvenhancer.prompt;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.ai.chat.prompt.Prompt;

public class CreateCoverLetterPromptTest {

    @Test
    void testCreateCoverLetterPrompt() {
        String expectedResult = """
            This is the cv: "cv". This is the vacancy:"vacancy".
            Create a cover letter for my application.
            Your response must be in the language of the CV.
            """;
        Prompt prompt = CreateCoverLetterPrompt.getPrompt(new CvVacancyDto("cv", "vacancy"));
        String result = prompt.getContents();
        Assertions.assertEquals(expectedResult, result);
    }
}