package com.company.bugfixai.controller;

import com.company.bugfixai.dto.JiraWebhookRequest;
import com.company.bugfixai.service.JiraService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/webhook")
public class JiraWebhookController {

    private final JiraService jiraService;

    public JiraWebhookController(JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @PostMapping("/jira")
    public ResponseEntity<String> handleWebhook(
            @RequestBody JiraWebhookRequest request) {

        log.info("Webhook hit");

        // 🛡 SAFETY CHECKS
        if (request == null ||
                request.getIssue() == null ||
                request.getIssue().getKey() == null ||
                request.getIssue().getFields() == null ||
                request.getIssue().getFields().getDescription() == null) {

            log.error("Invalid Jira webhook payload: {}", request);
            return ResponseEntity.badRequest()
                    .body("Invalid Jira webhook payload");
        }

        String issueKey = request.getIssue().getKey();
        String description = request.getIssue().getFields().getDescription();

        log.info("Webhook received for issueKey={}", issueKey);

        jiraService.processWebhook(issueKey, description);

        log.info("Webhook processing completed for issueKey={}", issueKey);

        return ResponseEntity.ok("OK");
    }
}
