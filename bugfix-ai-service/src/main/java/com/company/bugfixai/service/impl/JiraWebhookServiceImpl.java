package com.company.bugfixai.service.impl;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.dto.JiraWebhookRequest;
import com.company.bugfixai.service.BugAnalysisService;
import com.company.bugfixai.service.JiraWebhookService;
import org.springframework.stereotype.Service;

@Service
public class JiraWebhookServiceImpl implements JiraWebhookService {

    private final BugAnalysisService bugAnalysisService;

    public JiraWebhookServiceImpl(BugAnalysisService bugAnalysisService) {
        this.bugAnalysisService = bugAnalysisService;
    }

    @Override
    public void processWebhook(JiraWebhookRequest request) {

        String description = request.getIssue().getFields().getDescription();

        AiBugAnalysisResponse aiResponse = bugAnalysisService.analyzeBug(description);

        // For now just log it
        System.out.println("AI Analysis Result:");
        System.out.println(aiResponse);
    }
}
