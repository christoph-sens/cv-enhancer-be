package com.christophsens.cvenhancer.prompt;

import com.christophsens.cvenhancer.dto.CvVacancyDto;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

public class CreateCoverLetterPrompt {
    private CreateCoverLetterPrompt() {
    }

    private static final String TEMPLATE = """
        This is the cv: "{cv}". This is the vacancy:"{vacancy}".
        Create a cover letter for my application.
        Your response must be in the language of the CV.
        """;
    
    public static Prompt getPrompt(CvVacancyDto dto) {
        PromptTemplate promptTemplate = new PromptTemplate(TEMPLATE);
        promptTemplate.add("cv", dto.cv());
        promptTemplate.add("vacancy", dto.vacancy());
        return promptTemplate.create();
    }
}