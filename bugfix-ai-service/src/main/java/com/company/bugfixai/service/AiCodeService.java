package com.company.bugfixai.service;

import com.company.bugfixai.dto.AiBugAnalysisResponse;

public interface AiCodeService {

    AiBugAnalysisResponse parse(String aiResponse);
}
