package com.serpanalyzer.serp_analyzer_backend.search;

import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;

import java.util.List;

public interface SearchProvider {

    List<SearchResult> search(String query, int limit);
}
