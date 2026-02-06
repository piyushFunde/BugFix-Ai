package com.company.bugfixai.service;

import com.company.bugfixai.dto.AiBugAnalysisResponse;

public interface JiraCommentService {
    void addComment(String issueKey, AiBugAnalysisResponse response);
}
