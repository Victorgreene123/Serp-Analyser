package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnalysisServiceTest {

    private final AnalysisService analysisService = new AnalysisService();

    @Test
    void analyzeReturnsAnalysisResponse() {
        AnalysisRequest request = new AnalysisRequest("deep learning papers", 10, "JOURNAL");

        AnalysisResponse response = analysisService.analyze(request);

        assertThat(response).isNotNull();
        assertThat(response.getTotalDocuments()).isZero();
        assertThat(response.getFeatures()).isEmpty();
        assertThat(response.getProcessingTime()).isZero();
    }
}
