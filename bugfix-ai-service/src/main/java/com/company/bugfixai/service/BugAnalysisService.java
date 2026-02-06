package com.company.bugfixai.service;
import com.company.bugfixai.controller.BugAnalyzeRequest;
import com.company.bugfixai.dto.AiBugAnalysisResponse;

public interface BugAnalysisService {
     AiBugAnalysisResponse analyzeBug(String description);

     AiBugAnalysisResponse analyze(String issueKey, String description);

     AiBugAnalysisResponse analyze(BugAnalyzeRequest request);

     AiBugAnalysisResponse analyze(String bugDescription);
}
