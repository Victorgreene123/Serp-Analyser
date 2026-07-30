package com.serpanalyzer.serp_analyzer_backend.search;

import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;

import java.util.List;
import java.util.stream.IntStream;

public class MockSearchProvider implements SearchProvider {

    private static final List<SearchResult> MOCK_RESULTS = List.of(
            new SearchResult("1", "Crime Analysis Report", "https://example.com/crime1",
                    "A structured overview of crime reporting analysis.", "Mock"),
            new SearchResult("2", "AI Based Crime Detection", "https://example.com/crime2",
                    "Research notes on AI-assisted crime detection systems.", "Mock"),
            new SearchResult("3", "Deep Learning Crime Prediction", "https://example.com/crime3",
                    "A paper discussing deep learning for crime prediction.", "Mock"),
            new SearchResult("4", "Crime Reporting System Analysis", "https://example.com/crime4",
                    "Common headings and features in digital crime reporting systems.", "Mock"),
            new SearchResult("5", "Semantic Analysis of Journal Papers", "https://example.com/journal1",
                    "Journal-paper structure and semantic section extraction.", "Mock")
    );

    @Override
    public List<SearchResult> search(String query, int limit) {
        int resultCount = Math.min(limit, MOCK_RESULTS.size());

        return IntStream.range(0, resultCount)
                .mapToObj(MOCK_RESULTS::get)
                .toList();
    }
}
