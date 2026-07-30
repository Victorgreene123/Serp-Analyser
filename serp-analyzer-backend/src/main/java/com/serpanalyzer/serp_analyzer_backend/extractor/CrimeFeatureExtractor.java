package com.serpanalyzer.serp_analyzer_backend.extractor;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.nlp.KeywordDictionary;
import com.serpanalyzer.serp_analyzer_backend.nlp.PatternMatcher;
import com.serpanalyzer.serp_analyzer_backend.parser.DocumentParser;
import com.serpanalyzer.serp_analyzer_backend.parser.TextNormalizer;
import org.springframework.stereotype.Component;

@Component
public class CrimeFeatureExtractor implements FeatureExtractor {

    private final DocumentParser documentParser;
    private final TextNormalizer textNormalizer;
    private final KeywordDictionary keywordDictionary;
    private final PatternMatcher patternMatcher;

    public CrimeFeatureExtractor(DocumentParser documentParser, PatternMatcher patternMatcher) {
        this.documentParser = documentParser;
        this.patternMatcher = patternMatcher;
        this.textNormalizer = new TextNormalizer();
        this.keywordDictionary = new KeywordDictionary();
    }

    @Override
    public boolean supports(AnalysisType analysisType) {
        return AnalysisType.CRIME == analysisType;
    }

    @Override
    public ExtractionResult extractFeatures(PageContent content) {
        String normalizedText = textNormalizer.normalizeForMatching(documentParser.parse(content));
        return ExtractionResult.features(patternMatcher.findMatches(normalizedText, keywordDictionary.crimeFeatures()));
    }
}
