package com.serpanalyzer.serp_analyzer_backend.ranking;

public class FeatureStatistics {

    private String featureName;
    private int frequency;
    private double percentage;
    private int rank;

    public FeatureStatistics() {
    }

    public FeatureStatistics(String featureName, int frequency, double percentage, int rank) {
        this.featureName = featureName;
        this.frequency = frequency;
        this.percentage = percentage;
        this.rank = rank;
    }

    public String getFeatureName() {
        return featureName;
    }

    public int getFrequency() {
        return frequency;
    }

    public double getPercentage() {
        return percentage;
    }

    public int getRank() {
        return rank;
    }
}
