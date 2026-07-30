package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;

import java.util.List;

public class CrawlResult {

    private final List<PageContent> pages;
    private final long processingTime;

    public CrawlResult(List<PageContent> pages, long processingTime) {
        this.pages = pages;
        this.processingTime = processingTime;
    }

    public List<PageContent> getPages() {
        return pages;
    }

    public long getProcessingTime() {
        return processingTime;
    }

    public int getSuccessfulPages() {
        return (int) pages.stream()
                .filter(page -> "SUCCESS".equals(page.getStatus()))
                .count();
    }

    public int getFailedPages() {
        return pages.size() - getSuccessfulPages();
    }
}
