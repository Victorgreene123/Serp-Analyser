package com.serpanalyzer.serp_analyzer_backend.parser;

public class TextNormalizer {

    public String normalize(String text) {
        if (text == null || text.isBlank()) {
            return "";
        }

        return text
                .replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", " ")
                .replaceAll("[^\\p{Alnum}\\s:.-]", " ")
                .replaceAll("\\s+", " ")
                .trim();
    }

    public String normalizeForMatching(String text) {
        return normalize(text).toLowerCase();
    }
}
