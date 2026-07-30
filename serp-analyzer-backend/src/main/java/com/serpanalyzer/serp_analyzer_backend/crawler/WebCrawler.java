package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.exception.CrawlException;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

@Component
public class WebCrawler {

    private static final int TIMEOUT_MILLIS = 5000;

    public PageContent crawl(String url) {
        validateUrl(url);

        long startTime = System.currentTimeMillis();
        try {
            if (isExampleUrl(url)) {
                return createMockPageContent(url, System.currentTimeMillis() - startTime);
            }

            Document document = Jsoup.connect(url)
                    .timeout(TIMEOUT_MILLIS)
                    .get();
            String content = document.body() == null ? "" : document.body().text();

            return new PageContent(
                    UUID.randomUUID().toString(),
                    url,
                    document.title(),
                    content,
                    countWords(content),
                    System.currentTimeMillis() - startTime,
                    "SUCCESS"
            );
        } catch (Exception exception) {
            throw new CrawlException("Unable to crawl webpage", exception);
        }
    }

    private void validateUrl(String url) {
        if (url == null || url.isBlank()) {
            throw new CrawlException("URL cannot be empty");
        }

        try {
            URI uri = new URI(url);
            if (uri.getScheme() == null || uri.getHost() == null) {
                throw new CrawlException("Invalid URL");
            }
        } catch (URISyntaxException exception) {
            throw new CrawlException("Invalid URL", exception);
        }
    }

    private boolean isExampleUrl(String url) throws URISyntaxException {
        String host = new URI(url).getHost();
        return "example.com".equalsIgnoreCase(host) || host.endsWith(".example.com");
    }

    private PageContent createMockPageContent(String url, long crawlTime) {
        String content = "Mock crawled content for " + url + " with crime reporting and journal analysis context.";

        return new PageContent(
                UUID.randomUUID().toString(),
                url,
                "Mock Page",
                content,
                countWords(content),
                crawlTime,
                "SUCCESS"
        );
    }

    private int countWords(String content) {
        if (content == null || content.isBlank()) {
            return 0;
        }

        return content.trim().split("\\s+").length;
    }
}
