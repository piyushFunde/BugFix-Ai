package com.company.bugfixai.service.impl;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.service.JiraClient;
import com.company.bugfixai.service.JiraCommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JiraCommentServiceImpl implements JiraCommentService {

    private final JiraClient jiraClient;

    public JiraCommentServiceImpl(JiraClient jiraClient) {
        this.jiraClient = jiraClient;
    }

    @Override
    public void addComment(String issueKey, AiBugAnalysisResponse response) {

        String commentText = """
                AI Bug Analysis

                Root Cause:
                %s

                Suggested Fix:
                %s

                Code Snippet:
                %s
                """.formatted(
                response.getRootCause(),
                response.getFixExplanation(),
                response.getCodeSnippet()
        );

        jiraClient.addComment(issueKey, commentText);
        log.info("Jira comment posted successfully for issueKey: {}", issueKey);
    }
}
