package com.company.bugfixai.service.impl;

import com.company.bugfixai.dto.AiBugAnalysisResponse;
import com.company.bugfixai.service.AiCodeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

@Service
    public class AiCodeServiceImpl implements AiCodeService {

        private final ObjectMapper objectMapper = new ObjectMapper();

        @Override
        public AiBugAnalysisResponse parse(String aiResponse) {
            try {
                return objectMapper.readValue(
                        aiResponse,
                        AiBugAnalysisResponse.class
                );
            } catch (Exception e) {
                throw new RuntimeException("Failed to parse AI response", e);
            }
        }
    }

