package com.serpanalyzer.serp_analyzer_backend.nlp;

import java.util.List;
import java.util.Map;

public class KeywordDictionary {

    public Map<String, List<String>> crimeFeatures() {
        return Map.ofEntries(
                Map.entry("Crime Type", List.of("crime type", "offence", "offense", "incident type")),
                Map.entry("Crime Location", List.of("crime location", "location", "scene", "address")),
                Map.entry("Date of Incident", List.of("date of incident", "incident date", "reported on")),
                Map.entry("Time of Incident", List.of("time of incident", "incident time", "at approximately")),
                Map.entry("Victim Information", List.of("victim", "complainant", "injured party")),
                Map.entry("Suspect Information", List.of("suspect", "accused", "perpetrator")),
                Map.entry("Police Statement", List.of("police statement", "police said", "officer stated")),
                Map.entry("Witness Statement", List.of("witness", "eyewitness", "statement")),
                Map.entry("Evidence", List.of("evidence", "exhibit", "forensic")),
                Map.entry("Arrest Information", List.of("arrest", "detained", "taken into custody")),
                Map.entry("Court Proceedings", List.of("court", "trial", "proceedings", "charged")),
                Map.entry("Source Attribution", List.of("source", "reported by", "according to"))
        );
    }

    public Map<String, List<String>> journalHeadings() {
        return Map.ofEntries(
                Map.entry("Abstract", List.of("abstract")),
                Map.entry("Introduction", List.of("introduction")),
                Map.entry("Related Work", List.of("related work")),
                Map.entry("Literature Review", List.of("literature review")),
                Map.entry("Methodology", List.of("methodology", "methods", "method")),
                Map.entry("Dataset", List.of("dataset", "data set", "data")),
                Map.entry("Model Architecture", List.of("model architecture", "architecture")),
                Map.entry("Training", List.of("training")),
                Map.entry("Experiments", List.of("experiments", "experimental setup")),
                Map.entry("Results", List.of("results")),
                Map.entry("Discussion", List.of("discussion")),
                Map.entry("Conclusion", List.of("conclusion")),
                Map.entry("References", List.of("references", "bibliography"))
        );
    }
}
