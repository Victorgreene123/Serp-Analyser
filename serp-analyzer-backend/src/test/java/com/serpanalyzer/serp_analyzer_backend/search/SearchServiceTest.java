package com.serpanalyzer.serp_analyzer_backend.search;

import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SearchServiceTest {

    private final SearchService searchService = new SearchService(new MockSearchProvider());

    @Test
    void searchReturnsSearchResults() {
        List<SearchResult> results = searchService.search("crime papers", 3);

        assertThat(results).hasSize(3);
        assertThat(results.getFirst().getTitle()).isEqualTo("Crime Analysis Report");
        assertThat(results.getFirst().getUrl()).isEqualTo("https://example.com/crime1");
        assertThat(results.getFirst().getSource()).isEqualTo("Mock");
    }
}
