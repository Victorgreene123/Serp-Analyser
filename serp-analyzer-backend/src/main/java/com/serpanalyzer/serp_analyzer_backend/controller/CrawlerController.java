package com.serpanalyzer.serp_analyzer_backend.controller;

import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlResult;
import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlerService;
import com.serpanalyzer.serp_analyzer_backend.dto.CrawlRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.CrawlResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CrawlerController {

    private final CrawlerService crawlerService;

    public CrawlerController(CrawlerService crawlerService) {
        this.crawlerService = crawlerService;
    }

    @PostMapping("/crawl")
    public CrawlResponse crawl(@Valid @RequestBody CrawlRequest request) {
        CrawlResult crawlResult = crawlerService.crawlUrls(request.getUrls());

        return new CrawlResponse(
                crawlResult.getPages().size(),
                crawlResult.getSuccessfulPages(),
                crawlResult.getFailedPages(),
                crawlResult.getProcessingTime()
        );
    }
}
