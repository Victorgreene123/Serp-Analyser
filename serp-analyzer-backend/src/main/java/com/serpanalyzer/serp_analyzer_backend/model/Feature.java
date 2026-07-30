package com.serpanalyzer.serp_analyzer_backend.model;

public class Feature {

    private String name;
    private int frequency;
    private String category;

    public Feature() {
    }

    public Feature(String name, int frequency, String category) {
        this.name = name;
        this.frequency = frequency;
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public int getFrequency() {
        return frequency;
    }

    public String getCategory() {
        return category;
    }
}
