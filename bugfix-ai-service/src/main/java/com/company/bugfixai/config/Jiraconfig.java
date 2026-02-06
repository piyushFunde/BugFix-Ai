package com.company.bugfixai.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

@Component
    @ConfigurationProperties(prefix = "jira")
    @Getter
    @Setter
    public class Jiraconfig {
        private String baseUrl;
        private String email;
        private String apiToken;

    public HttpHeaders authHeaders() {
        return null;
    }
}


