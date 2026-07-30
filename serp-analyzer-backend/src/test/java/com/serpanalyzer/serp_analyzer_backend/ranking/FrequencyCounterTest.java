package com.serpanalyzer.serp_analyzer_backend.ranking;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class FrequencyCounterTest {

    private final FrequencyCounter frequencyCounter = new FrequencyCounter();

    @Test
    void countReturnsFrequencyForEveryValue() {
        Map<String, Integer> frequencies = frequencyCounter.count(List.of(
                "Crime Location",
                "Crime Location",
                "Victim Information"
        ));

        assertThat(frequencies).containsEntry("Crime Location", 2);
        assertThat(frequencies).containsEntry("Victim Information", 1);
    }
}
