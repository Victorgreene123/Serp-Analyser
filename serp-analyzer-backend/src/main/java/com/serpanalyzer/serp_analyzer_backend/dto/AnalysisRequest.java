package com.serpanalyzer.serp_analyzer_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class AnalysisRequest {

    @NotBlank(message = "Query cannot be empty")
    private String query;

    @Min(value = 1, message = "Limit must be greater than zero")
    private int limit;

    @NotBlank(message = "Analysis type must be provided")
    private String analysisType;

    public AnalysisRequest() {
    }

    public AnalysisRequest(String query, int limit, String analysisType) {
        this.query = query;
        this.limit = limit;
        this.analysisType = analysisType;
    }

    public String getQuery() {
        return query;
    }

    public int getLimit() {
        return limit;
    }

    public String getAnalysisType() {
        return analysisType;
    }
}
