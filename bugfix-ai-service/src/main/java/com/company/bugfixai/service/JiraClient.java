package com.company.bugfixai.service;

public interface JiraClient {
    void addComment(String issueKey, String commentBody);
}
