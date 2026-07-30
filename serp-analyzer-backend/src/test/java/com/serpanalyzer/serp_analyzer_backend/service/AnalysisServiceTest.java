package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlerService;
import com.serpanalyzer.serp_analyzer_backend.crawler.WebCrawler;
import com.serpanalyzer.serp_analyzer_backend.extractor.CrimeFeatureExtractor;
import com.serpanalyzer.serp_analyzer_backend.extractor.JournalHeadingExtractor;
import com.serpanalyzer.serp_analyzer_backend.nlp.PatternMatcher;
import com.serpanalyzer.serp_analyzer_backend.parser.DocumentParser;
import com.serpanalyzer.serp_analyzer_backend.ranking.FrequencyCounter;
import com.serpanalyzer.serp_analyzer_backend.ranking.RankingEngine;
import com.serpanalyzer.serp_analyzer_backend.ranking.RankingService;
import com.serpanalyzer.serp_analyzer_backend.search.MockSearchProvider;
import com.serpanalyzer.serp_analyzer_backend.search.SearchService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisServiceTest {

    private static final DocumentParser DOCUMENT_PARSER = new DocumentParser();
    private static final PatternMatcher PATTERN_MATCHER = new PatternMatcher();

    private final AnalysisService analysisService = new AnalysisService(
            new SearchService(new MockSearchProvider()),
            new CrawlerService(Executors.newFixedThreadPool(5), new WebCrawler()),
            List.of(
                    new CrimeFeatureExtractor(DOCUMENT_PARSER, PATTERN_MATCHER),
                    new JournalHeadingExtractor(DOCUMENT_PARSER, PATTERN_MATCHER)
            ),
            new RankingService(new FrequencyCounter(), new RankingEngine())
    );

    @Test
    void analyzeReturnsAnalysisResponse() {
        AnalysisRequest request = new AnalysisRequest("deep learning papers", 10, "JOURNAL");

        AnalysisResponse response = analysisService.analyze(request);

        assertThat(response).isNotNull();
        assertThat(response.getTotalDocuments()).isEqualTo(5);
        assertThat(response.getDocumentsAnalyzed()).isEqualTo(5);
        assertThat(response.getFeatures()).isEmpty();
        assertThat(response.getHeadings()).extracting("name").contains("Abstract", "Methodology", "Results");
        assertThat(response.getRankedHeadings()).extracting("heading").contains("Abstract", "Methodology", "Results");
        assertThat(response.getSummary()).contains("journal heading");
        assertThat(response.getProcessingTime()).isGreaterThanOrEqualTo(0);
    }

    @Test
    void analyzeSelectsCrimeExtractor() {
        AnalysisRequest request = new AnalysisRequest("crime reporting systems", 10, "CRIME");

        AnalysisResponse response = analysisService.analyze(request);

        assertThat(response.getFeatures()).extracting("name").contains("Crime Location", "Police Statement", "Evidence");
        assertThat(response.getRankedFeatures()).extracting("featureName").contains("Crime Location", "Police Statement", "Evidence");
        assertThat(response.getSummary()).contains("crime-reporting feature");
        assertThat(response.getHeadings()).isEmpty();
    }
}
