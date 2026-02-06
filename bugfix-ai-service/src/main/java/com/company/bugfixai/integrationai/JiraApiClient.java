package com.company.bugfixai.integrationai;

import com.company.bugfixai.dto.JiraIssueDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class JiraApiClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${jira.base-url}")
    private String jiraBaseUrl;

    @Value("${jira.email}")
    private String jiraEmail;

    @Value("${jira.api-token}")
    private String jiraApiToken;

    public JiraIssueDTO getIssue(String issueKey) {

        String url = jiraBaseUrl + "/rest/api/3/issue/" + issueKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setBasicAuth(jiraEmail, jiraApiToken);
        headers.setAccept(MediaType.parseMediaTypes("application/json"));

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<JiraIssueDTO> response =
                restTemplate.exchange(url, HttpMethod.GET, entity, JiraIssueDTO.class);

        return response.getBody();
    }

    public String fetchIssueDescription(String issueKey) {
        JiraIssueDTO issue = getIssue(issueKey);
        if (issue == null || issue.getFields() == null) {
            return null;
        }
        return issue.getFields().getDescription();
    }
}
