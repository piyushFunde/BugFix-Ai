package com.company.bugfixai.service.impl;

import com.company.bugfixai.service.JiraClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@Service
public class JiraClientImpl implements JiraClient {

    private final RestTemplate restTemplate;
    private final String authHeader;
    private final String baseUrl;

    public JiraClientImpl(
            @Value("${jira.base-url}") String baseUrl,
            @Value("${jira.email}") String email,
            @Value("${jira.api-token}") String token) {

        this.baseUrl = baseUrl;
        this.restTemplate = new RestTemplate();

        String auth = email + ":" + token;
        this.authHeader = "Basic " +
                Base64.getEncoder().encodeToString(auth.getBytes());
    }

    @Override
    public void addComment(String issueKey, String commentBody) {

        String url = baseUrl + "/rest/api/3/issue/" + issueKey + "/comment";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", authHeader);

        String payload = """
        {
          "body": {
            "type": "doc",
            "version": 1,
            "content": [
              {
                "type": "paragraph",
                "content": [
                  { "type": "text", "text": "%s" }
                ]
              }
            ]
          }
        }
        """.formatted(commentBody.replace("\"", "'"));

        HttpEntity<String> entity = new HttpEntity<>(payload, headers);
        restTemplate.postForEntity(url, entity, String.class);
    }
}
