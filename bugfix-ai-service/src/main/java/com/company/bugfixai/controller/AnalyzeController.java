//package com.company.bugfixai.controller;
//
//import com.company.bugfixai.dto.AiBugAnalysisResponse;
//import com.company.bugfixai.dto.AnalyzeRequest;
//import com.company.bugfixai.service.JiraService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class AnalyzeController {
//
//    private final JiraService jiraService;
//
//    @PostMapping("/analyze")
//    public AiBugAnalysisResponse analyze(@RequestBody AnalyzeRequest request) {
//
//        if (request.getDescription() == null || request.getDescription().isBlank()) {
//            throw new RuntimeException("Bug description is empty");
//        }
//
//        return jiraService.analyzeIssueByKey(
//                request.getIssueKey(),
//                request.getDescription()
//        );
//    }
//}
