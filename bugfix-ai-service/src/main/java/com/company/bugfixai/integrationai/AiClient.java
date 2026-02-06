package com.company.bugfixai.integrationai;

import com.company.bugfixai.dto.AiBugAnalysisResponse;

public interface AiClient {

    String generate(String prompt);

    AiBugAnalysisResponse analyzeIssue(String issueKey);

    AiBugAnalysisResponse analyzeBug(String issueKey, String description);

    AiBugAnalysisResponse analyzeBug(String description);

    AiBugAnalysisResponse analyze(String prompt);
}
