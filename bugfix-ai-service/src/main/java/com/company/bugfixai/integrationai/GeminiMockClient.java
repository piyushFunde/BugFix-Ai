package com.company.bugfixai.integrationai;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
@Primary   // 🔥 IMPORTANT
public class GeminiMockClient implements AiClient {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String generate(String prompt) {
        // ✅ RETURN JSON, NOT TEXT
        return """
        {
          "rootCause": "NullPointerException occurs because user object is not null-checked",
          "fixExplanation": "Add a null check before accessing user.getName()",
          "codeSnippet": "if (user != null) { String name = user.getName(); }"
        }
        """;
    }

    @Override
    public AiBugAnalysisResponse analyzeIssue(String issueKey) {
        return null;
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String issueKey, String description) {
        return null;
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String description) {
        try {
            return objectMapper.readValue(generate("Mock prompt for: " + description), AiBugAnalysisResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }
    }

    @Override
    public AiBugAnalysisResponse analyze(String prompt) {
        try {
            return objectMapper.readValue(generate(prompt), AiBugAnalysisResponse.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse AI response", e);
        }
    }
}
