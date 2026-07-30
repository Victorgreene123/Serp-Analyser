package com.serpanalyzer.serp_analyzer_backend.ranking;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class FrequencyCounter {

    public Map<String, Integer> count(Collection<String> values) {
        Map<String, Integer> frequencies = new LinkedHashMap<>();

        values.stream()
                .filter(value -> value != null && !value.isBlank())
                .forEach(value -> frequencies.merge(value, 1, Integer::sum));

        return frequencies;
    }
}
