# Semantic SERP Analyzer
## Implementation Plan

**Course:** Java Concurrency  
**Architecture:** Spring Boot + ReactJS  
**Objective:** Build a multithreaded Semantic Search Engine Results Page (SERP) Analyzer that:
- Extracts and ranks distinctive features of crime-reporting papers.
- Extracts and ranks common sub-headings from deep learning journal papers.
- Performs concurrent crawling and semantic analysis.
- Visualizes the analyzed results.

---

# 1. System Overview

The system consists of two independent modules sharing the same backend infrastructure.

## Module 1 – Crime Reporting Paper Analyzer

- Accepts a search query.
- Retrieves Search Engine Results Pages (SERP).
- Downloads multiple pages concurrently.
- Extracts crime-reporting features.
- Counts feature occurrences.
- Categorizes features by frequency.
- Returns visualization-ready data.

---

## Module 2 – Deep Learning Journal Analyzer

- Accepts a search query.
- Retrieves journal papers.
- Downloads PDF/HTML documents concurrently.
- Extracts document headings.
- Counts heading occurrences.
- Categorizes headings.
- Returns visualization-ready data.

---

# 2. Technology Stack

## Backend

- Java 21
- Spring Boot 3
- Spring Web
- Maven
- Jsoup
- Apache PDFBox
- Lombok
- Jackson
- SLF4J + Logback

### Java Concurrency

- ExecutorService
- ThreadPoolExecutor
- Callable
- Future
- CompletableFuture
- ExecutorCompletionService
- ConcurrentHashMap
- AtomicInteger
- BlockingQueue

---

## Frontend

- React
- Vite
- Axios
- Tailwind CSS
- Recharts

---

## Development Tools

- IntelliJ IDEA
- VS Code
- Git
- GitHub
- Postman

---

# 3. System Architecture

```
                    React Frontend
                           │
                    REST API Calls
                           │
                    Spring Boot API
                           │
        ┌──────────────────┼───────────────────┐
        │                  │                   │
        ▼                  ▼                   ▼
 Search Service     Crawl Service      Analysis Service
        │                  │                   │
        └──────────────┬───┴───────────────┬───┘
                       ▼                   ▼
                 Thread Pool        Parser Engine
                       │
       ┌───────────────┼────────────────┐
       ▼               ▼                ▼
    Worker 1       Worker 2        Worker N
       │               │                │
 Download Page    Download Page   Download Page
       │               │                │
 Extract Text     Extract Text    Extract Text
       └───────────────┼────────────────┘
                       ▼
             Concurrent Aggregation
                       ▼
                 REST JSON Response
                       ▼
                React Visualization
```

---

# 4. Backend Structure

```
backend/

src/main/java/com/serp/

├── config/
│   ├── ExecutorConfig.java
│   └── CorsConfig.java
│
├── controller/
│   ├── AnalysisController.java
│   ├── CrimeController.java
│   └── JournalController.java
│
├── service/
│   ├── SearchService.java
│   ├── CrawlService.java
│   ├── AnalysisService.java
│   ├── CrimeAnalysisService.java
│   └── JournalAnalysisService.java
│
├── concurrency/
│   ├── CrawlTask.java
│   ├── PdfTask.java
│   ├── AnalysisTask.java
│   └── ThreadPoolManager.java
│
├── parser/
│   ├── HtmlParser.java
│   ├── PdfParser.java
│   ├── MetadataParser.java
│   └── TextCleaner.java
│
├── analysis/
│   ├── CrimeFeatureExtractor.java
│   ├── HeadingExtractor.java
│   ├── RankingEngine.java
│   └── Categorizer.java
│
├── model/
│   ├── SearchResult.java
│   ├── PageContent.java
│   ├── Feature.java
│   ├── Heading.java
│   └── AnalysisResult.java
│
├── dto/
│   ├── SearchRequest.java
│   ├── AnalysisResponse.java
│   └── FeatureResponse.java
│
├── util/
│
└── exception/
```

---

# 5. Frontend Structure

```
frontend/

src/

├── components/
│   ├── Navbar.jsx
│   ├── SearchForm.jsx
│   ├── FeatureTable.jsx
│   ├── HeadingTable.jsx
│   ├── SummaryCards.jsx
│   ├── FeatureChart.jsx
│   ├── PieChart.jsx
│   ├── LoadingSpinner.jsx
│   └── ErrorCard.jsx
│
├── pages/
│   ├── Home.jsx
│   ├── CrimeAnalysis.jsx
│   ├── JournalAnalysis.jsx
│   └── Dashboard.jsx
│
├── services/
│   └── api.js
│
├── hooks/
│
├── utils/
│
├── App.jsx
│
└── main.jsx
```

---

# Phase 1 — Project Initialization

## Backend

- Create Spring Boot project.
- Configure Maven.
- Configure CORS.
- Create package structure.
- Configure thread pool.

Deliverable

- Backend starts successfully.

---

## Frontend

- Create React application using Vite.
- Install dependencies.
- Configure routing.
- Create initial layout.

Deliverable

- Frontend starts successfully.

---

# Phase 2 — API Development

Develop REST endpoints.

## Crime Analysis

```
POST /api/crime/analyze
```

---

## Journal Analysis

```
POST /api/journal/analyze
```

---

## Health Check

```
GET /api/status
```

Deliverable

Frontend communicates successfully with backend.

---

# Phase 3 — Search Service

Responsibilities

- Accept search query.
- Retrieve SERP results.
- Return URLs and snippets.

Output

```
SearchResult

Title

URL

Snippet
```

Deliverable

Search results retrieved successfully.

---

# Phase 4 — Concurrent Crawling

Configure

```
ExecutorService
```

Each URL becomes

```
Callable<PageContent>
```

Each worker

```
Download page

↓

Extract raw HTML

↓

Return PageContent
```

Deliverable

Concurrent downloading of multiple pages.

---

# Phase 5 — Parsing

Convert downloaded content into clean text.

Components

- HtmlParser
- PdfParser
- TextCleaner

Output

```
Clean text
```

Deliverable

Structured textual content.

---

# Phase 6 — Feature Extraction

## Crime Reporting Features

Extract

- Headline
- Date
- Location
- Crime Type
- Victim
- Suspect
- Police Statement
- Investigation
- Arrest
- Court Proceedings
- Evidence
- Witness
- Timeline
- Quotes

Each page returns

```
FeatureResult
```

---

## Deep Learning Papers

Extract

- Abstract
- Introduction
- Related Work
- Literature Review
- Methodology
- Dataset
- Model
- Training
- Results
- Discussion
- Conclusion
- References

Deliverable

Each document converted into structured data.

---

# Phase 7 — Concurrent Aggregation

Use

```
ConcurrentHashMap<String, AtomicInteger>
```

instead of

```
HashMap
```

Workflow

```
Thread

↓

Feature

↓

ConcurrentHashMap

↓

AtomicInteger.incrementAndGet()
```

Deliverable

Thread-safe aggregation.

---

# Phase 8 — Ranking & Categorization

Sort features by frequency.

Categories

```
Very Common

Common

Rare
```

Generate

- Top features
- Top headings
- Percentages
- Processing time
- Total documents

Deliverable

Ranked analysis.

---

# Phase 9 — Visualization

Frontend renders

## Crime Dashboard

- Search form
- Summary cards
- Feature table
- Bar chart
- Pie chart

---

## Journal Dashboard

- Heading table
- Bar chart
- Pie chart

Deliverable

Interactive dashboards.

---

# Phase 10 — Optimization

Implement

- Retry mechanism
- Timeout handling
- Batch downloads
- Logging
- Performance metrics
- Graceful shutdown

Performance comparison

Sequential

```
100 pages

↓

180 seconds
```

Concurrent

```
100 pages

↓

20 seconds
```

Deliverable

Performance report demonstrating multithreading benefits.

---

# REST API

## Analyze Crime Papers

```
POST /api/crime/analyze
```

Request

```json
{
  "query": "crime reporting papers"
}
```

Response

```json
{
  "totalPages": 100,
  "processingTime": 5400,
  "features": [
    {
      "name": "Headline",
      "count": 95,
      "category": "Very Common"
    }
  ]
}
```

---

## Analyze Journal Papers

```
POST /api/journal/analyze
```

Request

```json
{
  "query": "deep learning models"
}
```

Response

```json
{
  "headings": [
    {
      "name": "Introduction",
      "count": 97
    }
  ]
}
```

---

# Threading Workflow

```
User Search

↓

Search Service

↓

Retrieve URLs

↓

ExecutorService

↓

Worker Threads

↓

Download HTML/PDF

↓

Parser

↓

Feature Extraction

↓

ConcurrentHashMap

↓

Ranking

↓

REST Response

↓

React Dashboard
```

---

# Recommended Development Timeline

## Week 1

- Project setup
- Backend architecture
- Frontend setup
- API skeleton

---

## Week 2

- Search integration
- Concurrent crawling
- HTML parsing

---

## Week 3

- PDF parsing
- Feature extraction
- Heading extraction

---

## Week 4

- Ranking
- Categorization
- Visualization

---

## Week 5

- Testing
- Optimization
- Documentation
- Final presentation

---

# Expected Deliverables

- Spring Boot REST API
- React Web Application
- Concurrent Web Crawler
- Crime Feature Analyzer
- Journal Heading Analyzer
- Interactive Dashboard
- Performance Evaluation
- Project Report
- Source Code Repository

---

# Future Enhancements

- AI-powered semantic search using sentence embeddings.
- Export reports to PDF and Excel.
- User authentication.
- Search history.
- Scheduled background crawling.
- Real-time progress updates using WebSockets.
- Docker deployment.
- CI/CD pipeline with GitHub Actions.
- Database persistence for historical analyses.