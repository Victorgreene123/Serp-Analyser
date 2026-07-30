package com.serpanalyzer.serp_analyzer_backend.nlp;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class PatternMatcher {

    public boolean containsAny(String text, List<String> keywords) {
        if (text == null || text.isBlank()) {
            return false;
        }

        return keywords.stream().anyMatch(text::contains);
    }

    public List<String> findMatches(String text, Map<String, List<String>> patterns) {
        return patterns.entrySet().stream()
                .filter(entry -> containsAny(text, entry.getValue()))
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
