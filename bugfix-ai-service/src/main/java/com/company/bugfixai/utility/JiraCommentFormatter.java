package com.company.bugfixai.utility;

import com.company.bugfixai.dto.AiBugAnalysisResponse;

public class JiraCommentFormatter {

    public static String format(AiBugAnalysisResponse response) {
        return """
        🤖 *AI Bug Analysis*

        🔍 *Root Cause*
        %s

        🛠 *Fix*
        %s

        💻 *Code Snippet*
        {code}
        %s
        {code}
        """.formatted(
                response.getRootCause(),
                response.getFixExplanation(),
                response.getCodeSnippet()
        );
    }
}
