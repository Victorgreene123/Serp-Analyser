package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlerService;
import com.serpanalyzer.serp_analyzer_backend.crawler.WebCrawler;
import com.serpanalyzer.serp_analyzer_backend.search.MockSearchProvider;
import com.serpanalyzer.serp_analyzer_backend.search.SearchService;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisServiceTest {

    private final AnalysisService analysisService = new AnalysisService(
            new SearchService(new MockSearchProvider()),
            new CrawlerService(Executors.newFixedThreadPool(5), new WebCrawler())
    );

    @Test
    void analyzeReturnsAnalysisResponse() {
        AnalysisRequest request = new AnalysisRequest("deep learning papers", 10, "JOURNAL");

        AnalysisResponse response = analysisService.analyze(request);

        assertThat(response).isNotNull();
        assertThat(response.getTotalDocuments()).isEqualTo(5);
        assertThat(response.getFeatures()).isEmpty();
        assertThat(response.getProcessingTime()).isGreaterThanOrEqualTo(0);
    }
}
