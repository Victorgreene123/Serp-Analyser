package com.serpanalyzer.serp_analyzer_backend.analysis;

import com.serpanalyzer.serp_analyzer_backend.dto.ExtractedFeature;
import com.serpanalyzer.serp_analyzer_backend.dto.ExtractedHeading;

import java.util.List;

public class AnalysisResult {

    private final int documentsAnalyzed;
    private final List<ExtractedFeature> features;
    private final List<ExtractedHeading> headings;
    private final List<String> featureOccurrences;
    private final List<String> headingOccurrences;

    public AnalysisResult(int documentsAnalyzed, List<ExtractedFeature> features, List<ExtractedHeading> headings) {
        this(documentsAnalyzed, features, headings, List.of(), List.of());
    }

    public AnalysisResult(
            int documentsAnalyzed,
            List<ExtractedFeature> features,
            List<ExtractedHeading> headings,
            List<String> featureOccurrences,
            List<String> headingOccurrences
    ) {
        this.documentsAnalyzed = documentsAnalyzed;
        this.features = features;
        this.headings = headings;
        this.featureOccurrences = featureOccurrences;
        this.headingOccurrences = headingOccurrences;
    }

    public int getDocumentsAnalyzed() {
        return documentsAnalyzed;
    }

    public List<ExtractedFeature> getFeatures() {
        return features;
    }

    public List<ExtractedHeading> getHeadings() {
        return headings;
    }

    public List<String> getFeatureOccurrences() {
        return featureOccurrences;
    }

    public List<String> getHeadingOccurrences() {
        return headingOccurrences;
    }
}
