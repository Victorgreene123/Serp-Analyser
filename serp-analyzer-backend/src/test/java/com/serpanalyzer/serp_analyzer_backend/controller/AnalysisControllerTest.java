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
class AnalysisControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void analyzeReturnsOk() throws Exception {
        mockMvc.perform(post("/api/analyze")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                    "query": "crime reports",
                                    "limit": 10,
                                    "analysisType": "CRIME"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalDocuments").value(0))
                .andExpect(jsonPath("$.features").isArray())
                .andExpect(jsonPath("$.processingTime").value(0));
    }
}
