package com.serpanalyzer.serp_analyzer_backend.ranking;

import com.serpanalyzer.serp_analyzer_backend.analysis.AnalysisType;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RankingServiceTest {

    private final RankingService rankingService = new RankingService(new FrequencyCounter(), new RankingEngine());

    @Test
    void generateReportCalculatesRankFrequencyPercentageAndSummary() {
        AnalysisReport report = rankingService.generateReport(
                AnalysisType.CRIME,
                4,
                List.of("Crime Location", "Crime Location", "Crime Location", "Police Statement"),
                List.of(),
                120
        );

        assertThat(report.getRankedFeatures()).hasSize(2);
        assertThat(report.getRankedFeatures().getFirst().getFeatureName()).isEqualTo("Crime Location");
        assertThat(report.getRankedFeatures().getFirst().getFrequency()).isEqualTo(3);
        assertThat(report.getRankedFeatures().getFirst().getPercentage()).isEqualTo(75.0);
        assertThat(report.getRankedFeatures().getFirst().getRank()).isEqualTo(1);
        assertThat(report.getSummary()).contains("Crime Location");
    }

    @Test
    void generateReportIncludesRankedHeadings() {
        AnalysisReport report = rankingService.generateReport(
                AnalysisType.JOURNAL,
                2,
                List.of(),
                List.of("Abstract", "Abstract", "Results"),
                80
        );

        assertThat(report.getRankedHeadings()).hasSize(2);
        assertThat(report.getRankedHeadings().getFirst().getHeading()).isEqualTo("Abstract");
        assertThat(report.getRankedHeadings().getFirst().getPercentage()).isEqualTo(100.0);
        assertThat(report.getSummary()).contains("Abstract");
    }
}
