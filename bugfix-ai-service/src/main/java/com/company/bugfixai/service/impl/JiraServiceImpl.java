package com.company.bugfixai.service.impl;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.integrationai.JiraApiClient;
import com.company.bugfixai.service.BugAnalysisService;
import com.company.bugfixai.service.JiraCommentService;
import com.company.bugfixai.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JiraServiceImpl implements JiraService {

    private final BugAnalysisService bugAnalysisService;
    private final JiraCommentService jiraCommentService;

    public JiraServiceImpl(BugAnalysisService bugAnalysisService,
                           JiraCommentService jiraCommentService) {
        this.bugAnalysisService = bugAnalysisService;
        this.jiraCommentService = jiraCommentService;
    }

    // 🔹 Webhook OR API usage
    @Override
    public AiBugAnalysisResponse processWebhook(String issueKey, String description) {

        log.info("AI analysis started for issueKey={}", issueKey);

        AiBugAnalysisResponse response =
                bugAnalysisService.analyzeBug(description);

        log.info("Root Cause: {}", response.getRootCause());
        log.info("Fix: {}", response.getFixExplanation());

        if (issueKey != null && !issueKey.isBlank()) {
            jiraCommentService.addComment(issueKey, response);
            log.info("Jira comment posted successfully for issueKey={}", issueKey);
        }

        return response;
    }

    @Override
    public AiBugAnalysisResponse analyzeIssueByKey(String issueKey) {
        return null;
    }

    // 🔹 Web UI usage (IMPORTANT)
    @Override
    public AiBugAnalysisResponse analyzeIssueByKey(String issueKey, String description) {

        log.info("AI analysis started for issueKey={}", issueKey);

        AiBugAnalysisResponse response =
                bugAnalysisService.analyzeBug(description);

        log.info("Root Cause: {}", response.getRootCause());
        log.info("Fix: {}", response.getFixExplanation());

        return response; // 🔥 RETURN to UI
    }


    }
