package com.serpanalyzer.serp_analyzer_backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CrawlerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void crawlReturnsCrawlStats() throws Exception {
        mockMvc.perform(post("/api/crawl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "urls": [
                                        "https://example.com/page1",
                                        "https://example.com/page2"
                                    ]
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalPages").value(2))
                .andExpect(jsonPath("$.successfulPages").value(2))
                .andExpect(jsonPath("$.failedPages").value(0))
                .andExpect(jsonPath("$.processingTime").isNumber());
    }
}
