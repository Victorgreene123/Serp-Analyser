package com.serpanalyzer.serp_analyzer_backend.model;

public class JournalHeading {

    private String heading;
    private int frequency;

    public JournalHeading() {
    }

    public JournalHeading(String heading, int frequency) {
        this.heading = heading;
        this.frequency = frequency;
    }

    public String getHeading() {
        return heading;
    }

    public int getFrequency() {
        return frequency;
    }
}
