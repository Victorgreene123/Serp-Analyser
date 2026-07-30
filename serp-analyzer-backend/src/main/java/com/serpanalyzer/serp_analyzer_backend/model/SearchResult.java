package com.serpanalyzer.serp_analyzer_backend.model;

public class SearchResult {

    private String id;
    private String title;
    private String url;
    private String snippet;
    private String source;

    public SearchResult() {
    }

    public SearchResult(String title, String url, String snippet) {
        this(null, title, url, snippet, null);
    }

    public SearchResult(String id, String title, String url, String snippet, String source) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.snippet = snippet;
        this.source = source;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSnippet() {
        return snippet;
    }

    public String getSource() {
        return source;
    }
}
