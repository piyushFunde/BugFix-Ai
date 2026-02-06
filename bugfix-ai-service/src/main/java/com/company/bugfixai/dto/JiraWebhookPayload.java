package com.company.bugfixai.dto;

public class JiraWebhookPayload {

    private JiraIssueDTO issue;

    public JiraIssueDTO getIssue() {
        return issue;
    }

    public void setIssue(JiraIssueDTO issue) {
        this.issue = issue;
    }
}
