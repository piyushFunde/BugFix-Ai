package com.company.bugfixai.controller;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.service.BugAnalysisService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class ApiAnalyzeController {

    private final BugAnalysisService bugAnalysisService;

    @PostMapping(value = "/analyze", produces = "application/json")
    public ResponseEntity<AiBugAnalysisResponse> analyze(@RequestBody Map<String, String> req) {
        try {
            String bugDescription = req.get("bugDescription");
            System.out.println("Received bug description: " + bugDescription);
            AiBugAnalysisResponse response = bugAnalysisService.analyze(bugDescription);
            System.out.println("Analysis response: " + response);
            if (response == null) {
                throw new RuntimeException("Analysis returned null");
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Exception in analyze: {}", e.getMessage(), e);
            AiBugAnalysisResponse errorResponse = new AiBugAnalysisResponse();
            errorResponse.setRootCause("Error analyzing bug");
            errorResponse.setFixExplanation(e.getMessage());
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("rate limit")) {
                return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse);
            }
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }
}





