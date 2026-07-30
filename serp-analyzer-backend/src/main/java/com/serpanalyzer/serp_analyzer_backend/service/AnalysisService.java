package com.serpanalyzer.serp_analyzer_backend.service;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisResult;
import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisRequest;
import com.serpanalyzer.serp_analyzer_backend.dto.AnalysisResponse;
import com.serpanalyzer.serp_analyzer_backend.dto.ExtractedFeature;
import com.serpanalyzer.serp_analyzer_backend.dto.ExtractedHeading;
import com.serpanalyzer.serp_analyzer_backend.crawler.CrawlerService;
import com.serpanalyzer.serp_analyzer_backend.exception.SearchException;
import com.serpanalyzer.serp_analyzer_backend.extractor.ExtractionResult;
import com.serpanalyzer.serp_analyzer_backend.extractor.FeatureExtractor;
import com.serpanalyzer.serp_analyzer_backend.model.PageContent;
import com.serpanalyzer.serp_analyzer_backend.model.SearchResult;
import com.serpanalyzer.serp_analyzer_backend.ranking.AnalysisReport;
import com.serpanalyzer.serp_analyzer_backend.ranking.RankingService;
import com.serpanalyzer.serp_analyzer_backend.search.SearchService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class AnalysisService {

    private final SearchService searchService;
    private final CrawlerService crawlerService;
    private final List<FeatureExtractor> featureExtractors;
    private final RankingService rankingService;

    public AnalysisService(
            SearchService searchService,
            CrawlerService crawlerService,
            List<FeatureExtractor> featureExtractors,
            RankingService rankingService
    ) {
        this.searchService = searchService;
        this.crawlerService = crawlerService;
        this.featureExtractors = featureExtractors;
        this.rankingService = rankingService;
    }

    public AnalysisResponse analyze(AnalysisRequest request) {
        long startTime = System.currentTimeMillis();
        AnalysisType analysisType = parseAnalysisType(request.getAnalysisType());
        List<SearchResult> searchResults = searchService.search(request.getQuery(), request.getLimit());
        List<PageContent> pages = crawlerService.crawlSearchResults(searchResults);
        AnalysisResult analysisResult = analyzePages(pages, analysisType);
        long processingTime = System.currentTimeMillis() - startTime;
        AnalysisReport analysisReport = rankingService.generateReport(
                analysisType,
                analysisResult.getDocumentsAnalyzed(),
                analysisResult.getFeatureOccurrences(),
                analysisResult.getHeadingOccurrences(),
                processingTime
        );

        return new AnalysisResponse(
                analysisType,
                pages.size(),
                analysisResult.getDocumentsAnalyzed(),
                analysisResult.getFeatures(),
                analysisResult.getHeadings(),
                analysisReport.getRankedFeatures(),
                analysisReport.getRankedHeadings(),
                analysisReport.getSummary(),
                processingTime
        );
    }

    public AnalysisResult analyzePages(List<PageContent> pages, AnalysisType analysisType) {
        FeatureExtractor extractor = featureExtractors.stream()
                .filter(candidate -> candidate.supports(analysisType))
                .findFirst()
                .orElseThrow(() -> new SearchException("Unsupported analysis type"));

        Set<String> features = new LinkedHashSet<>();
        Set<String> headings = new LinkedHashSet<>();
        List<String> featureOccurrences = new ArrayList<>();
        List<String> headingOccurrences = new ArrayList<>();

        pages.stream()
                .filter(page -> "SUCCESS".equals(page.getStatus()))
                .map(extractor::extractFeatures)
                .forEach(result -> collectExtractionResult(result, features, headings, featureOccurrences, headingOccurrences));

        int documentsAnalyzed = (int) pages.stream()
                .filter(page -> "SUCCESS".equals(page.getStatus()))
                .count();

        return new AnalysisResult(
                documentsAnalyzed,
                features.stream().map(ExtractedFeature::new).toList(),
                headings.stream().map(ExtractedHeading::new).toList(),
                featureOccurrences,
                headingOccurrences
        );
    }

    private void collectExtractionResult(
            ExtractionResult result,
            Set<String> features,
            Set<String> headings,
            List<String> featureOccurrences,
            List<String> headingOccurrences
    ) {
        features.addAll(result.getFeatures());
        headings.addAll(result.getHeadings());
        featureOccurrences.addAll(result.getFeatures());
        headingOccurrences.addAll(result.getHeadings());
    }

    private AnalysisType parseAnalysisType(String analysisType) {
        try {
            return AnalysisType.valueOf(analysisType.toUpperCase());
        } catch (RuntimeException exception) {
            throw new SearchException("Unsupported analysis type", exception);
        }
    }
}
