package com.company.bugfixai.dto;

public class AiBugAnalysisResponse {

    private String rootCause;
    private String fixExplanation;
    private String codeSnippet;

    public AiBugAnalysisResponse() {
    }

    public AiBugAnalysisResponse(String rootCause, String fixExplanation, String codeSnippet) {
        this.rootCause = rootCause;
        this.fixExplanation = fixExplanation;
        this.codeSnippet = codeSnippet;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getFixExplanation() {
        return fixExplanation;
    }

    public void setFixExplanation(String fixExplanation) {
        this.fixExplanation = fixExplanation;
    }

    public String getCodeSnippet() {
        return codeSnippet;
    }

    public void setCodeSnippet(String codeSnippet) {
        this.codeSnippet = codeSnippet;
    }
}
