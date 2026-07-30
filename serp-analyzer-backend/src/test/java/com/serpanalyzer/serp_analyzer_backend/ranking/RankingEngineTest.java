package com.serpanalyzer.serp_analyzer_backend.ranking;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RankingEngineTest {

    private final RankingEngine rankingEngine = new RankingEngine();

    @Test
    void rankSortsByDescendingFrequency() {
        Map<String, Integer> frequencies = new LinkedHashMap<>();
        frequencies.put("A", 3);
        frequencies.put("B", 7);
        frequencies.put("C", 1);

        Map<String, Integer> ranked = rankingEngine.rank(frequencies);

        assertThat(ranked.keySet()).containsExactlyElementsOf(List.of("B", "A", "C"));
    }
}
