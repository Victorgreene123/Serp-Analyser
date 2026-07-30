package com.serpanalyzer.serp_analyzer_backend.extractor;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.nlp.PatternMatcher;
import com.serpanalyzer.serp_analyzer_backend.parser.DocumentParser;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class JournalHeadingExtractorTest {

    private final JournalHeadingExtractor extractor = new JournalHeadingExtractor(new DocumentParser(), new PatternMatcher());

    @Test
    void extractFeaturesDetectsAcademicHeadings() {
        PageContent pageContent = new PageContent(
                "https://example.com/journal",
                "Abstract Introduction Methodology Dataset Results Conclusion References",
                "Journal",
                7
        );

        ExtractionResult result = extractor.extractFeatures(pageContent);

        assertThat(result.getHeadings())
                .contains("Abstract", "Introduction", "Methodology", "Dataset", "Results", "Conclusion", "References");
    }
}
