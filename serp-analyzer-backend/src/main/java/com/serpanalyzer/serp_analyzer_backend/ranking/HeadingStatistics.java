package com.serpanalyzer.serp_analyzer_backend.ranking;

public class HeadingStatistics {

    private String heading;
    private int frequency;
    private double percentage;
    private int rank;

    public HeadingStatistics() {
    }

    public HeadingStatistics(String heading, int frequency, double percentage, int rank) {
        this.heading = heading;
        this.frequency = frequency;
        this.percentage = percentage;
        this.rank = rank;
    }

    public String getHeading() {
        return heading;
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
