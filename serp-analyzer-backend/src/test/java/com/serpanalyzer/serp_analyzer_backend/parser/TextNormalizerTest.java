package com.serpanalyzer.serp_analyzer_backend.parser;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class TextNormalizerTest {

    private final TextNormalizer textNormalizer = new TextNormalizer();

    @Test
    void normalizeRemovesDuplicateWhitespaceAndSymbols() {
        String normalizedText = textNormalizer.normalize("  Crime!!!    Location\t\t:  Lagos   ");

        assertThat(normalizedText).isEqualTo("Crime Location : Lagos");
    }
}
