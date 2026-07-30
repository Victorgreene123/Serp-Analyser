package com.serpanalyzer.serp_analyzer_backend.dto;

import java.util.List;
import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import com.serpanalyzer.serp_analyzer_backend.ranking.FeatureStatistics;
import com.serpanalyzer.serp_analyzer_backend.ranking.HeadingStatistics;

public class AnalysisResponse {

    private AnalysisType analysisType;
    private int totalDocuments;
    private int documentsAnalyzed;
    private List<ExtractedFeature> features;
    private List<ExtractedHeading> headings;
    private List<FeatureStatistics> rankedFeatures;
    private List<HeadingStatistics> rankedHeadings;
    private String summary;
    private long processingTime;

    public AnalysisResponse() {
    }

    public AnalysisResponse(int totalDocuments, List<ExtractedFeature> features, long processingTime) {
        this(null, totalDocuments, totalDocuments, features, List.of(), List.of(), List.of(), "", processingTime);
    }

    public AnalysisResponse(
            AnalysisType analysisType,
            int totalDocuments,
            int documentsAnalyzed,
            List<ExtractedFeature> features,
            List<ExtractedHeading> headings,
            List<FeatureStatistics> rankedFeatures,
            List<HeadingStatistics> rankedHeadings,
            String summary,
            long processingTime
    ) {
        this.analysisType = analysisType;
        this.totalDocuments = totalDocuments;
        this.documentsAnalyzed = documentsAnalyzed;
        this.features = features;
        this.headings = headings;
        this.rankedFeatures = rankedFeatures;
        this.rankedHeadings = rankedHeadings;
        this.summary = summary;
        this.processingTime = processingTime;
    }

    public AnalysisType getAnalysisType() {
        return analysisType;
    }

    public int getTotalDocuments() {
        return totalDocuments;
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

    public List<FeatureStatistics> getRankedFeatures() {
        return rankedFeatures;
    }

    public List<HeadingStatistics> getRankedHeadings() {
        return rankedHeadings;
    }

    public String getSummary() {
        return summary;
    }

    public long getProcessingTime() {
        return processingTime;
    }
}
