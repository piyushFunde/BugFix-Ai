//package com.company.bugfixai.controller;
//
//import com.company.bugfixai.service.JiraService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.web.bind.annotation.*;
//
////@RestController
////@RequiredArgsConstructor
//public class StaticAnalyzeController {
//
//    private final JiraService jiraService;
//
//    public StaticAnalyzeController(JiraService jiraService) {
//        this.jiraService = jiraService;
//    }
//
//    @PostMapping("/analyze")
//    public String analyze(
//            @RequestParam String issueKey,
//            @RequestParam String description) {
//
//        jiraService.processWebhook(issueKey, description);
//        return "AI analysis started successfully for " + issueKey;
//    }
//}
