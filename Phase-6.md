# Phase 6 — Frequency Analysis & Ranking Engine

## Overview

Phase 6 transforms the extracted features and headings from Phase 5 into meaningful analytical results.

The goal is to aggregate information across all analyzed documents, calculate how frequently each feature appears, rank them by occurrence, and prepare the data for frontend visualization.

This phase fulfills the assignment requirement to:

- Categorize distinctive features of crime-reporting papers based on the number of systems having the feature.
- Identify and rank distinct sub-headings of deep learning journal papers.
- Produce visualization-ready analytical data.

---

# Current System

After Phase 5, the system produces structured analysis for each crawled document.

Current pipeline:

```
User Query

      |

Search Service

      |

SERP Results

      |

Crawler Service

      |

PageContent

      |

Parser

      |

Feature Extraction

      |

Individual Analysis Results
```

Each document now contains extracted features or headings, but no global statistics.

---

# Phase 6 Goal

Transform:

```
Document 1

Crime Location

Victim

Police Statement

----------------

Document 2

Crime Location

Evidence

Witness

----------------

Document 3

Crime Location

Police Statement
```

Into:

```
Crime Location      3

Police Statement    2

Victim              1

Evidence            1

Witness             1
```

Sorted by frequency.

---

# New Backend Architecture

```
PageContent

      |

Parser

      |

Feature Extraction

      |

Feature Aggregator

      |

Frequency Counter

      |

Ranking Engine

      |

Analysis Report

      |

Frontend
```

---

# Package Structure

Create new package:

```
ranking
```

Recommended structure:

```
backend

├── ranking
│   ├── RankingService.java
│   ├── FrequencyCounter.java
│   ├── RankingEngine.java
│   ├── FeatureStatistics.java
│   ├── HeadingStatistics.java
│   └── AnalysisReport.java
```

---

# Task 1 — Feature Aggregation

## Purpose

Collect extracted features from every analyzed document.

Example:

```
Document A

Crime Location

Victim

Police Statement

----------------

Document B

Crime Location

Evidence

Police Statement
```

Combined collection:

```
Crime Location

Victim

Police Statement

Crime Location

Evidence

Police Statement
```

This collection becomes the input for frequency counting.

---

# Task 2 — Frequency Counter

## File

```
FrequencyCounter.java
```

Responsibilities:

- Count occurrences of every extracted feature.
- Count occurrences of every journal heading.
- Store totals.

Example output:

```
Crime Location → 18

Victim Information → 15

Police Statement → 12
```

Implementation should use efficient data structures such as:

```
Map<String, Integer>
```

---

# Task 3 — Ranking Engine

## File

```
RankingEngine.java
```

Purpose

Sort features by descending frequency.

Input:

```
Crime Location → 18

Police Statement → 12

Victim → 15
```

Output:

```
Crime Location → 18

Victim → 15

Police Statement → 12
```

The highest-frequency features should always appear first.

---

# Task 4 — Crime Feature Statistics

## File

```
FeatureStatistics.java
```

Responsibilities

Represent one ranked crime feature.

Fields:

```
featureName

frequency

percentage

rank
```

Example:

```json
{
  "featureName":"Crime Location",
  "frequency":18,
  "percentage":90,
  "rank":1
}
```

---

# Task 5 — Journal Heading Statistics

## File

```
HeadingStatistics.java
```

Responsibilities

Represent one ranked journal heading.

Fields:

```
heading

frequency

percentage

rank
```

Example:

```json
{
  "heading":"Abstract",
  "frequency":25,
  "percentage":100,
  "rank":1
}
```

---

# Task 6 — Analysis Report

## File

```
AnalysisReport.java
```

Purpose

Provide a single response object containing the complete analysis.

Suggested fields:

```
analysisType

documentsAnalyzed

processingTime

rankedFeatures

rankedHeadings

summary
```

Example:

```json
{
  "documentsAnalyzed":20,

  "processingTime":3500,

  "rankedFeatures":[...],

  "rankedHeadings":[...]
}
```

---

# Task 7 — Ranking Service

## File

```
RankingService.java
```

Responsibilities

Coordinate the ranking pipeline.

Workflow:

```
Extraction Results

↓

Aggregate Features

↓

Count Frequencies

↓

Calculate Percentages

↓

Sort by Frequency

↓

Generate Report
```

---

# Task 8 — Percentage Calculation

Besides frequency, calculate how common each feature is across all analyzed documents.

Formula:

```
Percentage

=

Feature Frequency

/

Documents Analyzed

×

100
```

Example:

```
Documents = 20

Crime Location = 18

Percentage = 90%
```

This provides richer analysis for visualization.

---

# Task 9 — Summary Generation

Automatically generate a textual summary.

Example:

```
20 documents were analyzed.

The most common crime-reporting feature was "Crime Location", appearing in 90% of the analyzed documents.

The most common journal heading was "Abstract", appearing in every paper analyzed.
```

This summary can be displayed directly in the frontend.

---

# Task 10 — Update Analysis API

Update:

```
POST /api/analyze
```

Response should now include:

```json
{
  "documentsAnalyzed":20,

  "processingTime":3500,

  "summary":"...",

  "rankedFeatures":[
    {
      "featureName":"Crime Location",
      "frequency":18,
      "percentage":90,
      "rank":1
    }
  ],

  "rankedHeadings":[
    {
      "heading":"Abstract",
      "frequency":20,
      "percentage":100,
      "rank":1
    }
  ]
}
```

---

# Task 11 — Performance Optimization

Ensure that:

- Frequency counting is performed efficiently.
- Large document collections are processed without unnecessary duplication.
- Ranking operations scale with increasing numbers of analyzed documents.

Where appropriate, leverage Java Streams for aggregation and sorting while maintaining readability.

---

# Task 12 — Testing

## Frequency Counter Test

Verify:

Input:

```
Crime Location

Crime Location

Victim
```

Output:

```
Crime Location → 2

Victim → 1
```

---

## Ranking Engine Test

Verify:

Input:

```
A → 3

B → 7

C → 1
```

Output:

```
B → 7

A → 3

C → 1
```

---

## Percentage Calculation Test

Verify percentages are correctly computed for different document counts.

---

## Analysis Report Test

Verify:

- Ranked features are included.
- Ranked headings are included.
- Summary is generated.
- Statistics are accurate.

---

# Phase 6 Completion Checklist

- [ ] Feature aggregation implemented
- [ ] Frequency counter completed
- [ ] Ranking engine implemented
- [ ] Percentage calculation completed
- [ ] Feature statistics model created
- [ ] Heading statistics model created
- [ ] Analysis report DTO created
- [ ] Ranking service completed
- [ ] Analysis endpoint updated
- [ ] Unit tests passing

---

# Expected Output

Example:

```json
{
  "documentsAnalyzed":20,
  "processingTime":3500,
  "summary":"20 documents analyzed. Crime Location was the most common feature.",

  "rankedFeatures":[
    {
      "featureName":"Crime Location",
      "frequency":18,
      "percentage":90,
      "rank":1
    },
    {
      "featureName":"Victim Information",
      "frequency":15,
      "percentage":75,
      "rank":2
    }
  ],

  "rankedHeadings":[
    {
      "heading":"Abstract",
      "frequency":20,
      "percentage":100,
      "rank":1
    },
    {
      "heading":"Introduction",
      "frequency":20,
      "percentage":100,
      "rank":2
    }
  ]
}
```

---

# Phase 6 Deliverable

At the completion of this phase, the backend will produce **fully ranked, statistical, and visualization-ready analysis reports**.

These reports can be consumed directly by the React frontend to generate:

- Feature ranking tables
- Journal heading ranking tables
- Bar charts
- Pie charts
- Summary cards
- Analytical dashboards

This completes all backend functionality required by the assignment. The remaining work is focused on presenting the results through the frontend.