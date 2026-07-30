package com.serpanalyzer.serp_analyzer_backend.extractor;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.nlp.PatternMatcher;
import com.serpanalyzer.serp_analyzer_backend.parser.DocumentParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrimeFeatureExtractorTest {

    private final CrimeFeatureExtractor extractor = new CrimeFeatureExtractor(new DocumentParser(), new PatternMatcher());

    @Test
    void extractFeaturesDetectsKnownCrimeReportingFeatures() {
        PageContent pageContent = new PageContent(
                "https://example.com/crime",
                "The police statement lists the crime location, victim information, suspect information, and evidence.",
                "Crime",
                12
        );

        ExtractionResult result = extractor.extractFeatures(pageContent);

        assertThat(result.getFeatures())
                .contains("Crime Location", "Victim Information", "Suspect Information", "Police Statement", "Evidence");
    }
}
