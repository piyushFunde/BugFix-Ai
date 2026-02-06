package com.company.bugfixai.dto;

import lombok.Data;

@Data
public class AnalyzeRequest {
    private String issueKey;
    private String bugDescription;
}
