package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlerService;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import com.serpanalyzer.serp_analyzer_backend.search.SearchService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    private final SearchService searchService;
    private final CrawlerService crawlerService;

    public AnalysisService(SearchService searchService, CrawlerService crawlerService) {
        this.searchService = searchService;
        this.crawlerService = crawlerService;
    }

    public AnalysisResponse analyze(AnalysisRequest request) {
        long startTime = System.currentTimeMillis();
        List<SearchResult> searchResults = searchService.search(request.getQuery(), request.getLimit());
        List<PageContent> pages = crawlerService.crawlSearchResults(searchResults);
        long processingTime = System.currentTimeMillis() - startTime;

        return new AnalysisResponse(pages.size(), List.of(), processingTime);
    }
}
