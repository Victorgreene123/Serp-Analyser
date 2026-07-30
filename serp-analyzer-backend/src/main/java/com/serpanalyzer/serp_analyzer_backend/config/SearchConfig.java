package com.serpanalyzer.serp_analyzer_backend.config;

import com.serpanalyzer.serp_analyzer_backend.search.MockSearchProvider;
import com.serpanalyzer.serp_analyzer_backend.search.SearchProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SearchConfig {

    @Bean
    public SearchProvider searchProvider() {
        return new MockSearchProvider();
    }
}
