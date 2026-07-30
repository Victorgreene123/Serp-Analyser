package com.serpanalyzer.serp_analyzer_backend.dto;

public class CrawlResponse {

    private int totalPages;
    private int successfulPages;
    private int failedPages;
    private long processingTime;

    public CrawlResponse() {
    }

    public CrawlResponse(int totalPages, int successfulPages, int failedPages, long processingTime) {
        this.totalPages = totalPages;
        this.successfulPages = successfulPages;
        this.failedPages = failedPages;
        this.processingTime = processingTime;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getSuccessfulPages() {
        return successfulPages;
    }

    public int getFailedPages() {
        return failedPages;
    }

    public long getProcessingTime() {
        return processingTime;
    }
}
