package com.serpanalyzer.serp_analyzer_backend.parser;

import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DocumentParserTest {

    private final DocumentParser documentParser = new DocumentParser();

    @Test
    void parseRemovesHtmlScriptAndStyleContent() {
        PageContent pageContent = new PageContent(
                "https://example.com",
                "<style>.hidden{}</style><script>alert('x')</script><h1>Introduction</h1><p>This paper...</p>",
                "Sample",
                2
        );

        String parsedText = documentParser.parse(pageContent);

        assertThat(parsedText).contains("Introduction");
        assertThat(parsedText).contains("This paper");
        assertThat(parsedText).doesNotContain("alert");
        assertThat(parsedText).doesNotContain(".hidden");
    }
}
