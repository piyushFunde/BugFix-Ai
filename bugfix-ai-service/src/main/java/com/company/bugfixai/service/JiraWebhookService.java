package com.company.bugfixai.service;

import com.company.bugfixai.dto.JiraWebhookRequest;

public interface JiraWebhookService {

    void processWebhook(JiraWebhookRequest request);
}
