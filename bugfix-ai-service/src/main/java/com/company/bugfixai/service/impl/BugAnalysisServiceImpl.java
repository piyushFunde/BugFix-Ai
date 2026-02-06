package com.company.bugfixai.service.impl;

import com.company.bugfixai.controller.BugAnalyzeRequest;
import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.integrationai.AiClient;
import com.company.bugfixai.service.BugAnalysisService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BugAnalysisServiceImpl implements BugAnalysisService {

    private final AiClient aiClient;

    public BugAnalysisServiceImpl(@Qualifier("geminiAiClient") AiClient aiClient) {
        this.aiClient = aiClient;
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String description) {
        return aiClient.analyzeBug(description);
    }

    @Override
    public AiBugAnalysisResponse analyze(String issueKey, String description) {
        return aiClient.analyzeBug(description);
    }

    @Override
    public AiBugAnalysisResponse analyze(BugAnalyzeRequest request) {
        return aiClient.analyzeBug(request.getBugDescription());
    }

    @Override
    public AiBugAnalysisResponse analyze(String bugDescription) {
        return aiClient.analyzeBug(bugDescription);
    }
}




