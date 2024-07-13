package com.christophsens.cvenhancer.prompt;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;

public class TranslationPrompt {

    private TranslationPrompt() {
    }

    private static final String TEMPLATE = """
        Translate the text according to iso693 "{languageCode}_{countryCode}"
        This is the cv: "{cv}"
        """;

    public static Prompt getPrompt(String cv, String languageCode, String countryCode) {
        PromptTemplate promptTemplate = new PromptTemplate(TEMPLATE);
        promptTemplate.add("cv", cv);
        promptTemplate.add("languageCode", languageCode.toLowerCase());
        promptTemplate.add("countryCode", countryCode.toUpperCase());
        return promptTemplate.create();
    }
}