package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

class CrawlerServiceTest {

    @Test
    void crawlUrlsReturnsPageContentForEveryUrl() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CrawlerService crawlerService = new CrawlerService(executorService, new WebCrawler());
        List<String> urls = List.of(
                "https://example.com/page1",
                "https://example.com/page2",
                "https://example.com/page3",
                "https://example.com/page4",
                "https://example.com/page5",
                "https://example.com/page6",
                "https://example.com/page7",
                "https://example.com/page8",
                "https://example.com/page9",
                "https://example.com/page10"
        );

        CrawlResult crawlResult = crawlerService.crawlUrls(urls);
        List<PageContent> pages = crawlResult.getPages();

        assertThat(pages).hasSize(10);
        assertThat(crawlResult.getSuccessfulPages()).isEqualTo(10);
        assertThat(crawlResult.getFailedPages()).isZero();

        executorService.shutdownNow();
    }
}
