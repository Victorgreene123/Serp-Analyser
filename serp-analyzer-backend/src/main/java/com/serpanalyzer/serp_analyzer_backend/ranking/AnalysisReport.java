package com.serpanalyzer.serp_analyzer_backend.ranking;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;

import java.util.List;

public class AnalysisReport {

    private AnalysisType analysisType;
    private int documentsAnalyzed;
    private long processingTime;
    private List<FeatureStatistics> rankedFeatures;
    private List<HeadingStatistics> rankedHeadings;
    private String summary;

    public AnalysisReport() {
    }

    public AnalysisReport(
            AnalysisType analysisType,
            int documentsAnalyzed,
            long processingTime,
            List<FeatureStatistics> rankedFeatures,
            List<HeadingStatistics> rankedHeadings,
            String summary
    ) {
        this.analysisType = analysisType;
        this.documentsAnalyzed = documentsAnalyzed;
        this.processingTime = processingTime;
        this.rankedFeatures = rankedFeatures;
        this.rankedHeadings = rankedHeadings;
        this.summary = summary;
    }

    public AnalysisType getAnalysisType() {
        return analysisType;
    }

    public int getDocumentsAnalyzed() {
        return documentsAnalyzed;
    }

    public long getProcessingTime() {
        return processingTime;
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
}
