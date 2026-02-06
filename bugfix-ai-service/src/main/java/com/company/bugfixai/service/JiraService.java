package com.company.bugfixai.service;


import com.company.bugfixai.dto.AiBugAnalysisResponse;

public interface JiraService {

    AiBugAnalysisResponse processWebhook(String issueKey, String description);

    AiBugAnalysisResponse analyzeIssueByKey(String issueKey);

    AiBugAnalysisResponse analyzeIssueByKey(String issueKey, String description);
}
