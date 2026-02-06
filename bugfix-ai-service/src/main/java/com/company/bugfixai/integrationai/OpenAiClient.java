package com.company.bugfixai.integrationai;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.List;

@Component
@ConditionalOnExpression("T(org.springframework.util.StringUtils).hasText('${openai.api.key:}')")
@Slf4j
public class OpenAiClient implements AiClient {

    private final OpenAiService service;
    private final ObjectMapper mapper = new ObjectMapper();

    public OpenAiClient(@Value("${openai.api.key}") String apiKey) {
        this.service = new OpenAiService(apiKey, Duration.ofSeconds(30));
    }

    @Override
    public AiBugAnalysisResponse analyze(String prompt) {
        return analyzeBug(prompt);
    }

    @Override
    public String generate(String prompt) {
        try {
            ChatCompletionRequest request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(List.of(new ChatMessage(ChatMessageRole.USER.value(), prompt)))
                    .build();

            ChatCompletionResult result = service.createChatCompletion(request);
            return result.getChoices().get(0).getMessage().getContent();
        } catch (Exception e) {
            log.error("OpenAI generate failed: {}", e.getMessage(), e);
            throw new RuntimeException("OpenAI generate failed", e);
        }
    }

    @Override
    public AiBugAnalysisResponse analyzeIssue(String issueKey) {
        return null;
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String issueKey, String description) {
        return analyzeBug(description);
    }

    @Override
    public AiBugAnalysisResponse analyzeBug(String description) {
        try {
            String prompt = """
            You are a senior Java backend engineer.
            Analyze the following bug and respond ONLY in JSON.

            Format:
            {
              "rootCause": "...",
              "fixExplanation": "...",
              "codeSnippet": "..."
            }

            Bug:
            %s
            """.formatted(description);

            String response = generate(prompt);

            // Try to parse as JSON
            try {
                return mapper.readValue(response, AiBugAnalysisResponse.class);
            } catch (Exception e) {
                // If not JSON, create response from text
                AiBugAnalysisResponse resp = new AiBugAnalysisResponse();
                resp.setRootCause("Analysis generated");
                resp.setFixExplanation(response);
                resp.setCodeSnippet("// Check the analysis above");
                return resp;
            }
        } catch (Exception e) {
            log.error("OpenAI analyzeBug failed: {}", e.getMessage(), e);
            throw new RuntimeException("OpenAI analyzeBug failed", e);
        }
    }
}
