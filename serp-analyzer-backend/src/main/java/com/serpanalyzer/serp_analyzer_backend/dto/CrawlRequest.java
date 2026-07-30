package com.serpanalyzer.serp_analyzer_backend.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public class CrawlRequest {

    @NotEmpty(message = "URLs must be provided")
    private List<String> urls;

    public CrawlRequest() {
    }

    public CrawlRequest(List<String> urls) {
        this.urls = urls;
    }

    public List<String> getUrls() {
        return urls;
    }
}
