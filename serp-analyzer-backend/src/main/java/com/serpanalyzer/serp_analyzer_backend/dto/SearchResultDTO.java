package com.serpanalyzer.serp_analyzer_backend.dto;

public class SearchResultDTO {

    private String title;
    private String url;
    private String snippet;

    public SearchResultDTO() {
    }

    public SearchResultDTO(String title, String url, String snippet) {
        this.title = title;
        this.url = url;
        this.snippet = snippet;
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
}
