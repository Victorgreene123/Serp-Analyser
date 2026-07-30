package com.serpanalyzer.serp_analyzer_backend.crawler;

import com.serpanalyzer.serp_analyzer_backend.exception.CrawlException;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class CrawlerService {

    private static final long TASK_TIMEOUT_SECONDS = 10;

    private final ExecutorService executorService;
    private final WebCrawler webCrawler;

    public CrawlerService(ExecutorService executorService, WebCrawler webCrawler) {
        this.executorService = executorService;
        this.webCrawler = webCrawler;
    }

    public List<PageContent> crawlSearchResults(List<SearchResult> searchResults) {
        if (searchResults == null || searchResults.isEmpty()) {
            return List.of();
        }

        List<String> urls = searchResults.stream()
                .map(SearchResult::getUrl)
                .toList();

        return crawlUrls(urls).getPages();
    }

    public CrawlResult crawlUrls(List<String> urls) {
        if (urls == null || urls.isEmpty()) {
            throw new CrawlException("URLs must be provided");
        }

        long startTime = System.currentTimeMillis();
        List<Future<PageContent>> futures = urls.stream()
                .map(url -> new CrawlerTask(url, webCrawler))
                .map(executorService::submit)
                .toList();

        List<PageContent> pages = new ArrayList<>();
        for (int index = 0; index < futures.size(); index++) {
            String url = urls.get(index);
            Future<PageContent> future = futures.get(index);
            pages.add(resolveFuture(url, future));
        }

        return new CrawlResult(pages, System.currentTimeMillis() - startTime);
    }

    private PageContent resolveFuture(String url, Future<PageContent> future) {
        try {
            return future.get(TASK_TIMEOUT_SECONDS, TimeUnit.SECONDS);
        } catch (InterruptedException exception) {
            Thread.currentThread().interrupt();
            return failedPage(url, "INTERRUPTED");
        } catch (ExecutionException exception) {
            return failedPage(url, "FAILED");
        } catch (TimeoutException exception) {
            future.cancel(true);
            return failedPage(url, "TIMEOUT");
        }
    }

    private PageContent failedPage(String url, String status) {
        return new PageContent(null, url, "", "", 0, 0, status);
    }
}
