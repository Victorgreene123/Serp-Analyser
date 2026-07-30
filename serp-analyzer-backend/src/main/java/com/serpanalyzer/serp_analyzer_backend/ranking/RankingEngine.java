package com.serpanalyzer.serp_analyzer_backend.ranking;

import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class RankingEngine {

    public Map<String, Integer> rank(Map<String, Integer> frequencies) {
        return frequencies.entrySet().stream()
                .sorted(
                        Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder())
                                .thenComparing(Map.Entry.comparingByKey())
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (left, right) -> left,
                        LinkedHashMap::new
                ));
    }
}
