package com.serpanalyzer.serp_analyzer_backend.controller;

import com.serpanalyzer.serp_analyzer_backend.dto.SearchRequest;
import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import com.serpanalyzer.serp_analyzer_backend.search.SearchService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping("/search")
    public List<SearchResult> search(@Valid @RequestBody SearchRequest request) {
        return searchService.search(request.getQuery(), request.getLimit());
    }
}
