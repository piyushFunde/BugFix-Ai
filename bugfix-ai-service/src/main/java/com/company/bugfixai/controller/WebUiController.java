//package com.company.bugfixai.controller;
//
//import com.company.bugfixai.dto.AiBugAnalysisResponse;
//import com.company.bugfixai.service.JiraService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//public class WebUiController {
//
//    private final JiraService jiraService;
//
//    @GetMapping("/")
//    public String index() {
//        return "index"; // loads index.html from templates
//    }
//
//    @PostMapping("/analyze")
//    public String analyze(@RequestParam String issueKey, Model model) {
//
//        AiBugAnalysisResponse result =
//                jiraService.analyzeIssueByKey(issueKey);
//
//        model.addAttribute("result", result);
//        return "index";
//    }
//}
