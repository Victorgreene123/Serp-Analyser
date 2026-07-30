package com.serpanalyzer.serp_analyzer_backend.dto;

import java.util.List;

public class AnalysisResponse {

    private int totalDocuments;
    private List<String> features;
    private long processingTime;

    public AnalysisResponse() {
    }

    public AnalysisResponse(int totalDocuments, List<String> features, long processingTime) {
        this.totalDocuments = totalDocuments;
        this.features = features;
        this.processingTime = processingTime;
    }

    public int getTotalDocuments() {
        return totalDocuments;
    }

    public List<String> getFeatures() {
        return features;
    }

    public long getProcessingTime() {
        return processingTime;
    }
}
