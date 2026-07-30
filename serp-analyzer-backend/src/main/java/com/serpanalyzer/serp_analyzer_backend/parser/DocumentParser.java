package com.serpanalyzer.serp_analyzer_backend.parser;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.springframework.stereotype.Component;

@Component
public class DocumentParser {

    private final HtmlCleaner htmlCleaner;
    private final TextNormalizer textNormalizer;

    public DocumentParser() {
        this(new HtmlCleaner(), new TextNormalizer());
    }

    public DocumentParser(HtmlCleaner htmlCleaner, TextNormalizer textNormalizer) {
        this.htmlCleaner = htmlCleaner;
        this.textNormalizer = textNormalizer;
    }

    public String parse(PageContent pageContent) {
        if (pageContent == null) {
            return "";
        }

        String cleanedText = htmlCleaner.clean(pageContent.getContent());
        return textNormalizer.normalize(cleanedText);
    }
}
