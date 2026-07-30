package com.serpanalyzer.serp_analyzer_backend.extractor;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;

public interface FeatureExtractor {

    boolean supports(AnalysisType analysisType);

    ExtractionResult extractFeatures(PageContent content);
}
