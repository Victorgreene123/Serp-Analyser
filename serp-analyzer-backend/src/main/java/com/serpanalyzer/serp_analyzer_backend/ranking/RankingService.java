package com.serpanalyzer.serp_analyzer_backend.ranking;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class RankingService {

    private final FrequencyCounter frequencyCounter;
    private final RankingEngine rankingEngine;

    public RankingService(FrequencyCounter frequencyCounter, RankingEngine rankingEngine) {
        this.frequencyCounter = frequencyCounter;
        this.rankingEngine = rankingEngine;
    }

    public AnalysisReport generateReport(
            AnalysisType analysisType,
            int documentsAnalyzed,
            Collection<String> features,
            Collection<String> headings,
            long processingTime
    ) {
        List<FeatureStatistics> rankedFeatures = toFeatureStatistics(
                rankingEngine.rank(frequencyCounter.count(features)),
                documentsAnalyzed
        );
        List<HeadingStatistics> rankedHeadings = toHeadingStatistics(
                rankingEngine.rank(frequencyCounter.count(headings)),
                documentsAnalyzed
        );

        return new AnalysisReport(
                analysisType,
                documentsAnalyzed,
                processingTime,
                rankedFeatures,
                rankedHeadings,
                generateSummary(documentsAnalyzed, rankedFeatures, rankedHeadings)
        );
    }

    private List<FeatureStatistics> toFeatureStatistics(Map<String, Integer> rankedFrequencies, int documentsAnalyzed) {
        AtomicInteger rank = new AtomicInteger(1);

        return rankedFrequencies.entrySet().stream()
                .map(entry -> new FeatureStatistics(
                        entry.getKey(),
                        entry.getValue(),
                        calculatePercentage(entry.getValue(), documentsAnalyzed),
                        rank.getAndIncrement()
                ))
                .toList();
    }

    private List<HeadingStatistics> toHeadingStatistics(Map<String, Integer> rankedFrequencies, int documentsAnalyzed) {
        AtomicInteger rank = new AtomicInteger(1);

        return rankedFrequencies.entrySet().stream()
                .map(entry -> new HeadingStatistics(
                        entry.getKey(),
                        entry.getValue(),
                        calculatePercentage(entry.getValue(), documentsAnalyzed),
                        rank.getAndIncrement()
                ))
                .toList();
    }

    double calculatePercentage(int frequency, int documentsAnalyzed) {
        if (documentsAnalyzed <= 0) {
            return 0;
        }

        return Math.round(((double) frequency / documentsAnalyzed) * 10000.0) / 100.0;
    }

    private String generateSummary(
            int documentsAnalyzed,
            List<FeatureStatistics> rankedFeatures,
            List<HeadingStatistics> rankedHeadings
    ) {
        if (documentsAnalyzed == 0) {
            return "No documents were analyzed.";
        }

        if (!rankedFeatures.isEmpty()) {
            FeatureStatistics topFeature = rankedFeatures.getFirst();
            return "%d documents were analyzed. The most common crime-reporting feature was \"%s\", appearing in %.2f%% of analyzed documents."
                    .formatted(documentsAnalyzed, topFeature.getFeatureName(), topFeature.getPercentage());
        }

        if (!rankedHeadings.isEmpty()) {
            HeadingStatistics topHeading = rankedHeadings.getFirst();
            return "%d documents were analyzed. The most common journal heading was \"%s\", appearing in %.2f%% of analyzed documents."
                    .formatted(documentsAnalyzed, topHeading.getHeading(), topHeading.getPercentage());
        }

        return "%d documents were analyzed. No matching features or headings were detected.".formatted(documentsAnalyzed);
    }
}
