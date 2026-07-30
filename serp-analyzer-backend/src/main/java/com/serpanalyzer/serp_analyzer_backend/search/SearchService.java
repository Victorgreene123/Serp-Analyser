package com.serpanalyzer.serp_analyzer_backend.search;

import com.serpanalyzer.serp_analyzer_backend.exception.SearchException;
import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SearchService {

    private final SearchProvider searchProvider;

    public SearchService(SearchProvider searchProvider) {
        this.searchProvider = searchProvider;
    }

    public List<SearchResult> search(String query, int limit) {
        if (query == null || query.isBlank()) {
            throw new SearchException("Search query cannot be empty");
        }

        if (limit <= 0) {
            throw new SearchException("Search limit must be greater than zero");
        }

        try {
            return searchProvider.search(query, limit);
        } catch (RuntimeException exception) {
            throw new SearchException("Search service unavailable", exception);
        }
    }
}
