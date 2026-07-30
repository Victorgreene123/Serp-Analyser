package com.serpanalyzer.serp_analyzer_backend.extractor;

import java.util.List;

public class ExtractionResult {

    private final List<String> features;
    private final List<String> headings;

    public ExtractionResult(List<String> features, List<String> headings) {
        this.features = features;
        this.headings = headings;
    }

    public static ExtractionResult features(List<String> features) {
        return new ExtractionResult(features, List.of());
    }

    public static ExtractionResult headings(List<String> headings) {
        return new ExtractionResult(List.of(), headings);
    }

    public List<String> getFeatures() {
        return features;
    }

    public List<String> getHeadings() {
        return headings;
    }
}
