# Phase 5 — Semantic Content Analysis & Feature Extraction

## Overview

Phase 5 implements the **Semantic Analysis Engine** of the SERP Analyzer.

The purpose of this phase is to transform the raw webpage content collected during Phase 4 into structured analytical information.

At the end of this phase, the system should be capable of:

- Parsing crawled webpages.
- Cleaning and preprocessing extracted text.
- Detecting distinctive features of crime-reporting papers.
- Detecting common sub-headings of deep learning journal papers.
- Returning structured analysis results for further ranking in Phase 6.

This phase satisfies the core analytical requirements of the assignment.

---

# Current System

The current backend pipeline is:

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
```

At this point the system has only downloaded documents.

The content has **not yet been analyzed**.

---

# Phase 5 Goal

Convert this:

```
PageContent

↓

Large Block of Raw Text
```

Into this:

```
PageContent

↓

Parser

↓

Extracted Features

↓

Analysis Result
```

---

# New Backend Architecture

```
Search Service

      |

SERP Results

      |

Crawler Service

      |

PageContent

      |

Content Parser

      |

Feature Extraction Engine

      |

Analysis Result

      |

Phase 6 Ranking Engine
```

---

# Package Structure

Create new packages:

```
analysis

parser

extractor

nlp
```

Recommended structure:

```
backend

├── analysis
│   ├── AnalysisService.java
│   ├── AnalysisResult.java
│   └── AnalysisType.java
│
├── parser
│   ├── DocumentParser.java
│   ├── HtmlCleaner.java
│   └── TextNormalizer.java
│
├── extractor
│   ├── FeatureExtractor.java
│   ├── CrimeFeatureExtractor.java
│   ├── JournalHeadingExtractor.java
│   └── ExtractionResult.java
│
└── nlp
    ├── KeywordDictionary.java
    └── PatternMatcher.java
```

---

# Task 1 — Document Parsing

## File

```
DocumentParser.java
```

## Responsibilities

Receive a `PageContent` object and prepare it for analysis.

The parser should:

- Remove HTML tags
- Remove scripts and styles
- Normalize whitespace
- Remove unnecessary symbols
- Preserve document structure

Input:

```
PageContent
```

Output:

```
CleanText
```

---

# Task 2 — HTML Cleaning

## File

```
HtmlCleaner.java
```

Responsibilities:

- Remove HTML
- Remove JavaScript
- Remove CSS
- Decode HTML entities

Example

Before:

```
<h1>Introduction</h1>

<p>This paper...</p>
```

After:

```
Introduction

This paper...
```

---

# Task 3 — Text Normalization

## File

```
TextNormalizer.java
```

Responsibilities

Normalize text before analysis.

Perform:

- lowercase conversion (when appropriate)
- whitespace normalization
- punctuation cleanup
- duplicate space removal

Output:

Consistent text for extraction.

---

# Task 4 — Create Feature Extraction Interface

## File

```
FeatureExtractor.java
```

Purpose

Create a common contract for all extractors.

Example methods:

```
extractFeatures(PageContent content)
```

This allows different extraction strategies without changing the analysis service.

---

# Task 5 — Crime Feature Extraction

## File

```
CrimeFeatureExtractor.java
```

Purpose

Extract distinctive characteristics from crime-reporting papers.

The extractor should identify features such as:

- Crime Type
- Crime Location
- Date of Incident
- Time of Incident
- Victim Information
- Suspect Information
- Police Statement
- Witness Statement
- Evidence
- Arrest Information
- Court Proceedings
- Source Attribution

The system should identify the presence of these features in each document.

Output example:

```json
{
  "features": [
    "Crime Location",
    "Victim Information",
    "Police Statement",
    "Evidence"
  ]
}
```

---

# Task 6 — Journal Heading Extraction

## File

```
JournalHeadingExtractor.java
```

Purpose

Detect common academic paper sections.

Recognize headings such as:

- Abstract
- Introduction
- Related Work
- Literature Review
- Methodology
- Dataset
- Model Architecture
- Training
- Experiments
- Results
- Discussion
- Conclusion
- References

Output example:

```json
{
  "headings": [
    "Abstract",
    "Introduction",
    "Methodology",
    "Results",
    "Conclusion"
  ]
}
```

---

# Task 7 — Analysis Service

## File

```
AnalysisService.java
```

Responsibilities

Coordinate the complete analysis pipeline.

Workflow:

```
PageContent

↓

Parser

↓

Feature Extractor

↓

Extraction Result

↓

Response DTO
```

The service should decide which extractor to use based on the selected analysis type.

---

# Task 8 — Analysis DTOs

Create DTOs for analysis results.

Examples:

```
AnalysisRequest

AnalysisResponse

ExtractedFeature

ExtractedHeading
```

The response should contain structured data instead of raw text.

---

# Task 9 — Analysis API

Endpoint

```
POST /api/analyze
```

Example Request

```json
{
  "query": "crime reporting systems",
  "analysisType": "CRIME",
  "limit": 10
}
```

Example Response

```json
{
  "documentsAnalyzed": 10,
  "features": [
    {
      "name": "Crime Location"
    },
    {
      "name": "Police Statement"
    }
  ]
}
```

---

# Task 10 — Strategy Pattern

Avoid large conditional statements.

Instead, implement separate extraction strategies.

```
FeatureExtractor

        ▲
        │
 ┌──────┴───────────┐
 │                  │
 ▼                  ▼

CrimeExtractor   JournalExtractor
```

This makes future analysis types easy to add.

---

# Task 11 — Testing

Test the parser.

Verify:

- HTML is removed correctly.
- Text is normalized.

Test crime extraction.

Verify:

- Known crime-reporting features are detected.

Test journal extraction.

Verify:

- Academic headings are correctly identified.

Test analysis service.

Verify:

- Correct extractor is selected.
- Structured results are returned.

---

# Phase 5 Completion Checklist

- [ ] Document parser implemented
- [ ] HTML cleaner implemented
- [ ] Text normalization completed
- [ ] Feature extraction interface created
- [ ] Crime feature extractor implemented
- [ ] Journal heading extractor implemented
- [ ] Analysis service completed
- [ ] Analysis endpoint implemented
- [ ] DTOs completed
- [ ] Unit tests passing

---

# Output of Phase 5

The backend will now produce structured analysis instead of raw page text.

Example:

```json
{
  "documentsAnalyzed": 10,
  "features": [
    "Crime Location",
    "Victim Information",
    "Evidence",
    "Police Statement"
  ],
  "headings": [
    "Abstract",
    "Introduction",
    "Methodology",
    "Results",
    "Conclusion"
  ]
}
```

---

# Preparation for Phase 6

Phase 6 will transform the extracted information into ranked analytical data.

It will implement:

- Frequency counting
- Feature aggregation
- Ranking algorithms
- Statistical summaries
- Visualization-ready response objects

The pipeline will become:

```
PageContent

↓

Parser

↓

Feature Extraction

↓

Frequency Counter

↓

Ranking Engine

↓

Analysis Report

↓

React Dashboard
```