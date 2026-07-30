package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

public class CrawlerTask implements Callable<PageContent> {

    private static final Logger logger = LoggerFactory.getLogger(CrawlerTask.class);

    private final String url;
    private final WebCrawler webCrawler;

    public CrawlerTask(String url, WebCrawler webCrawler) {
        this.url = url;
        this.webCrawler = webCrawler;
    }

    @Override
    public PageContent call() {
        String threadName = Thread.currentThread().getName();
        logger.info("{} started crawling: {}", threadName, url);

        PageContent pageContent = webCrawler.crawl(url);

        logger.info("{} completed crawling: {}", threadName, url);
        return pageContent;
    }
}
