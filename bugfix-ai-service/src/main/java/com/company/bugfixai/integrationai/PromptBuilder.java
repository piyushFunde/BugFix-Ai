package com.company.bugfixai.integrationai;

import org.springframework.stereotype.Component;

@Component
public class PromptBuilder {

    public String buildBugAnalysisPrompt(String description) {

        return """
                You are a senior software engineer with 10+ years of experience.
                
                Respond in EXACTLY this format.
                Do NOT change headings.
                Do NOT add extra text.
                
                Root Cause:
                <one sentence only>
                
                Fix Explanation:
                <one short paragraph>
                
                Code Snippet:
                <code OR N/A>
                
                Bug Description:
                %s
                """.formatted(description);
    }
}