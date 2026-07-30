package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnalysisService {

    public AnalysisResponse analyze(AnalysisRequest request) {
        return new AnalysisResponse(0, List.of(), 0);
    }
}
