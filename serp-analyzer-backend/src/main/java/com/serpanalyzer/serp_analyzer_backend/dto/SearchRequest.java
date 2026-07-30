package com.serpanalyzer.serp_analyzer_backend.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class SearchRequest {

    @NotBlank(message = "Query cannot be empty")
    private String query;

    @Min(value = 1, message = "Limit must be greater than zero")
    private int limit;

    public SearchRequest() {
    }

    public SearchRequest(String query, int limit) {
        this.query = query;
        this.limit = limit;
    }

    public String getQuery() {
        return query;
    }

    public int getLimit() {
        return limit;
    }
}
