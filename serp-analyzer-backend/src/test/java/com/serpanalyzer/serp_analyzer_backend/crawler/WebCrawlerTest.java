package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WebCrawlerTest {

    private final WebCrawler webCrawler = new WebCrawler();

    @Test
    void crawlReturnsPageContent() {
        PageContent pageContent = webCrawler.crawl("https://example.com/crime1");

        assertThat(pageContent).isNotNull();
        assertThat(pageContent.getUrl()).isEqualTo("https://example.com/crime1");
        assertThat(pageContent.getTitle()).isEqualTo("Mock Page");
        assertThat(pageContent.getWordCount()).isGreaterThan(0);
        assertThat(pageContent.getStatus()).isEqualTo("SUCCESS");
    }
}
