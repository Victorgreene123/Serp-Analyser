package com.serpanalyzer.serp_analyzer_backend.model;

public class PageContent {

    private String url;
    private String content;
    private String title;
    private int wordCount;

    public PageContent() {
    }

    public PageContent(String url, String content, String title, int wordCount) {
        this.url = url;
        this.content = content;
        this.title = title;
        this.wordCount = wordCount;
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
}
