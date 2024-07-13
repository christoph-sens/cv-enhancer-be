package com.christophsens.cvenhancer.prompt;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

public class ImprovePrompt {
    private static final String TEMPLATE = """
        Improve this cv: "{cv}".
        Your response must be in the language of the CV.
        """;

    private ImprovePrompt() {
    }

    public static Prompt getPrompt(String cv) {
        PromptTemplate promptTemplate = new PromptTemplate(TEMPLATE);
        promptTemplate.add("cv", cv);
        return promptTemplate.create();
    }
}