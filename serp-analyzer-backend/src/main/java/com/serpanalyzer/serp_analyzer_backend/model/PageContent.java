package com.serpanalyzer.serp_analyzer_backend.model;

public class PageContent {

    private String id;
    private String url;
    private String title;
    private String content;
    private int wordCount;
    private long crawlTime;
    private String status;

    public PageContent() {
    }

    public PageContent(String url, String content, String title, int wordCount) {
        this(null, url, title, content, wordCount, 0, "SUCCESS");
    }

    public PageContent(String id, String url, String title, String content, int wordCount, long crawlTime, String status) {
        this.id = id;
        this.url = url;
        this.title = title;
        this.content = content;
        this.wordCount = wordCount;
        this.crawlTime = crawlTime;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public int getWordCount() {
        return wordCount;
    }

    public long getCrawlTime() {
        return crawlTime;
    }

    public String getStatus() {
        return status;
    }
}
