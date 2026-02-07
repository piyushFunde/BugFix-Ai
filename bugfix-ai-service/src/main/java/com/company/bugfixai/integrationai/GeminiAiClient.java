package com.company.bugfixai.integrationai;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Component("geminiAiClient")
public class GeminiAiClient implements AiClient {

    private final String apiKey;
    private final String apiUrl;
    private final RestTemplate restTemplate;
    private final ObjectMapper mapper = new ObjectMapper();

    public GeminiAiClient(
            @Value("${gemini.api.key}") String apiKey,
            @Value("${gemini.api.url}") String apiUrl,
            RestTemplate restTemplate
    ) {
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
    }

    @Override
    public AiBugAnalysisResponse analyze(String prompt) {
        String response = generate(prompt);
        return parseResponse(response);
    }

    @Override
    public String generate(String prompt) {
        Map<String, Object> payload = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(payload, headers);
        int maxAttempts = 3;

        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                ResponseEntity<String> response =
                        restTemplate.postForEntity(buildUrlWithKey(), entity, String.class);
                return extractText(response.getBody());
            } catch (HttpClientErrorException.TooManyRequests e) {
                if (attempt == maxAttempts) {
                    throw new RuntimeException("Gemini rate limit exceeded. Please try again shortly.", e);
                }
                try {
                    Thread.sleep(1000L * attempt);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Gemini retry interrupted.", ie);
                }
            } catch (Exception e) {
                throw new RuntimeException("Gemini generate failed", e);
            }
        }

        throw new RuntimeException("Gemini generate failed.");
    }

    @Override
    public AiBugAnalysisResponse analyzeIssue(String issueKey) {
        String prompt = buildJsonPrompt("Issue Key: " + issueKey);
        return analyze(prompt);
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String issueKey, String description) {
        return analyzeBug(description);
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String description) {
        String prompt = buildJsonPrompt(description);
        return analyze(prompt);
    }

    private String buildUrlWithKey() {
        if (apiUrl.contains("?")) {
            return apiUrl + "&key=" + apiKey;
        }
        return apiUrl + "?key=" + apiKey;
    }

    private String buildJsonPrompt(String description) {
        return """
                You are a senior Java backend engineer.
                Analyze the following bug and respond ONLY in JSON.
                Do NOT wrap the JSON in markdown or code fences.
                Do NOT add any extra text.

                Format:
                {
                  "rootCause": "...",
                  "fixExplanation": "...",
                  "codeSnippet": "..."
                }

                Bug:
                %s
                """.formatted(description);
    }

    private String extractText(String body) throws Exception {
        if (body == null || body.isBlank()) {
            return "";
        }
        JsonNode root = mapper.readTree(body);
        JsonNode candidates = root.path("candidates");
        if (candidates.isArray() && candidates.size() > 0) {
            JsonNode parts = candidates.get(0).path("content").path("parts");
            if (parts.isArray() && parts.size() > 0) {
                return parts.get(0).path("text").asText("");
            }
        }
        return "";
    }

    private AiBugAnalysisResponse parseResponse(String response) {
        try {
            String normalized = normalizeJson(response);
            return mapper.readValue(normalized, AiBugAnalysisResponse.class);
        } catch (Exception e) {
            AiBugAnalysisResponse fallback = new AiBugAnalysisResponse();
            fallback.setRootCause("Analysis generated");
            fallback.setFixExplanation(response);
            fallback.setCodeSnippet("// Check the analysis above");
            return fallback;
        }
    }

    private String normalizeJson(String response) {
        if (response == null) {
            return "";
        }
        String trimmed = response.trim();
        if (trimmed.startsWith("```")) {
            trimmed = trimmed.replaceFirst("^```[a-zA-Z0-9_-]*\\s*", "");
            trimmed = trimmed.replaceFirst("\\s*```\\s*$", "");
        }
        int firstBrace = trimmed.indexOf('{');
        int lastBrace = trimmed.lastIndexOf('}');
        if (firstBrace >= 0 && lastBrace > firstBrace) {
            return trimmed.substring(firstBrace, lastBrace + 1);
        }
        return trimmed;
    }
}
