package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class CrawlerTaskTest {

    @Test
    void callReturnsPageContent() throws Exception {
        CrawlerTask crawlerTask = new CrawlerTask("https://example.com/crime2", new WebCrawler());

        PageContent pageContent = crawlerTask.call();

        assertThat(pageContent).isNotNull();
        assertThat(pageContent.getStatus()).isEqualTo("SUCCESS");
        assertThat(pageContent.getUrl()).isEqualTo("https://example.com/crime2");
    }
}
