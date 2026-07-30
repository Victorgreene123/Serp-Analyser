package com.serpanalyzer.serp_analyzer_backend.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlCleaner {

    public String clean(String rawText) {
        if (rawText == null || rawText.isBlank()) {
            return "";
        }

        Document document = Jsoup.parse(rawText);
        document.select("script, style").remove();

        return document.text();
    }
}
