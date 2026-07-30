# React Frontend Implementation Prompt - SERP Analyzer Dashboard

## Project Context

You are implementing the frontend for a Multithreaded Semantic SERP Analyzer system.

The backend is built with:

- Java 26
- Spring Boot
- REST APIs

The frontend will be built with:

- React
- Vite
- TypeScript
- Axios
- Recharts
- Tailwind CSS

The application analyzes Search Engine Results Pages (SERP) and extracts:

1. Distinctive features from crime-reporting papers.
2. Distinctive headings from deep learning journal papers.
3. Frequency ranking of extracted features/headings.
4. Visualization-ready percentages, ranks, and summaries.

The backend is complete through Phase 6 and now returns ranked statistical reports from `/api/analyze`.

---

## Frontend Goal

Build a professional research analytics dashboard that allows users to:

- Submit search queries.
- Select analysis category.
- Send requests to Spring Boot APIs.
- View backend connection status.
- View SERP results.
- View extracted features or headings.
- View ranked crime-reporting features.
- View ranked deep learning journal headings.
- View generated analysis summaries.
- Visualize ranked results using charts.

---

## Application Architecture

Use a component-based architecture:

```text
src
├── api
├── charts
├── components
├── hooks
├── pages
├── services
├── types
├── utils
└── App.tsx
```

---

## Required Technologies

Core:

- React
- Vite
- TypeScript

API communication:

- Axios

Routing:

- React Router

Visualization:

- Recharts

Styling:

- Tailwind CSS

---

## Main Application Layout

The application should contain:

- Navigation bar
- Search and analysis panel
- Backend status indicator
- Analysis summary cards
- Generated summary panel
- Ranked feature visualization
- Ranked heading visualization
- Ranked feature table
- Ranked heading table
- SERP search results

---

## Pages

Create:

```text
src/pages/Dashboard.tsx
```

Responsibilities:

- Display search form.
- Trigger analysis.
- Optionally trigger SERP search.
- Display returned analysis report.
- Display ranked charts.
- Display ranked tables.
- Display statistics.
- Display generated summary text.

Dashboard structure:

```text
Dashboard
├── SearchPanel
├── StatCards
├── SummaryPanel
├── RankingCharts
├── FeatureTable
├── HeadingTable
└── SERPResults
```

---

## Components

Create:

```text
src/components
```

### Navbar

File:

```text
Navbar.tsx
```

Display:

- Application name
- Navigation links
- Backend connection status

Example:

```text
SERP Analyzer
Dashboard
Analysis
Results
Backend: Online
```

---

### SearchPanel

File:

```text
SearchPanel.tsx
```

Fields:

- Search query
- Analysis type
- Number of results
- Analyze button
- Optional Search SERP button

Analysis type values must match the backend:

```text
CRIME
JOURNAL
```

Example request:

```json
{
  "query": "crime reporting systems",
  "limit": 10,
  "analysisType": "CRIME"
}
```

---

### StatCard

File:

```text
StatCard.tsx
```

Display:

- Total SERP documents
- Documents analyzed
- Processing time
- Number of ranked features
- Number of ranked headings
- Top item percentage

---

### SummaryPanel

File:

```text
SummaryPanel.tsx
```

Display the backend-generated summary:

```ts
analysisResponse.summary
```

Example:

```text
10 documents were analyzed. The most common crime-reporting feature was "Crime Location", appearing in 90.00% of analyzed documents.
```

---

### ResultCard

File:

```text
ResultCard.tsx
```

Display SERP result fields:

- Title
- URL
- Snippet
- Source

Backend `SearchResult` shape:

```json
{
  "id": "1",
  "title": "Crime Analysis Report",
  "url": "https://example.com/crime1",
  "snippet": "A structured overview...",
  "source": "Mock"
}
```

---

### FeatureTable

File:

```text
FeatureTable.tsx
```

Display `rankedFeatures`.

Columns:

| Rank | Feature | Frequency | Percentage |
|---:|---|---:|---:|
| 1 | Crime Location | 9 | 90% |
| 2 | Police Statement | 7 | 70% |

Use:

```ts
analysisResponse.rankedFeatures
```

---

### HeadingTable

File:

```text
HeadingTable.tsx
```

Display `rankedHeadings`.

Columns:

| Rank | Heading | Frequency | Percentage |
|---:|---|---:|---:|
| 1 | Abstract | 10 | 100% |
| 2 | Introduction | 10 | 100% |

Use:

```ts
analysisResponse.rankedHeadings
```

---

## Visualization Components

Create:

```text
src/charts
```

### RankingBarChart

File:

```text
RankingBarChart.tsx
```

Purpose:

- Display frequency ranking for either `rankedFeatures` or `rankedHeadings`.

Required chart data:

```ts
[
  { name: "Crime Location", frequency: 9, percentage: 90, rank: 1 },
  { name: "Police Statement", frequency: 7, percentage: 70, rank: 2 }
]
```

Use Recharts:

- `ResponsiveContainer`
- `BarChart`
- `Bar`
- `XAxis`
- `YAxis`
- `Tooltip`

---

### RankingPieChart

File:

```text
RankingPieChart.tsx
```

Purpose:

- Display percentage distribution for ranked features/headings.

Use Recharts:

- `ResponsiveContainer`
- `PieChart`
- `Pie`
- `Cell`
- `Tooltip`
- `Legend`

---

## API Layer

Create:

```text
src/services
```

### API Client

File:

```text
api.ts
```

Configure Axios:

```ts
import axios from "axios";

export const api = axios.create({
  baseURL: "http://localhost:8080",
});
```

---

### Analysis Service

File:

```text
analysisService.ts
```

Functions:

```ts
checkBackendStatus()
searchSERP(request)
crawlUrls(request)
analyzeQuery(request)
```

---

## Backend APIs

### Backend Status

GET:

```text
/api/status
```

Response:

```text
SERP Analyzer Backend Running
```

---

### Search API

POST:

```text
/api/search
```

Request:

```json
{
  "query": "crime reporting papers",
  "limit": 10
}
```

Response:

```json
[
  {
    "id": "1",
    "title": "Crime Analysis Report",
    "url": "https://example.com/crime1",
    "snippet": "A structured overview of crime reporting analysis.",
    "source": "Mock"
  }
]
```

---

### Crawl API

POST:

```text
/api/crawl
```

Request:

```json
{
  "urls": [
    "https://example.com/page1",
    "https://example.com/page2"
  ]
}
```

Response:

```json
{
  "totalPages": 2,
  "successfulPages": 2,
  "failedPages": 0,
  "processingTime": 1200
}
```

---

### Analysis API

POST:

```text
/api/analyze
```

Crime request:

```json
{
  "query": "crime reporting systems",
  "limit": 10,
  "analysisType": "CRIME"
}
```

Crime response:

```json
{
  "analysisType": "CRIME",
  "totalDocuments": 10,
  "documentsAnalyzed": 10,
  "features": [
    {
      "name": "Crime Location"
    }
  ],
  "headings": [],
  "rankedFeatures": [
    {
      "featureName": "Crime Location",
      "frequency": 9,
      "percentage": 90,
      "rank": 1
    }
  ],
  "rankedHeadings": [],
  "summary": "10 documents were analyzed. The most common crime-reporting feature was \"Crime Location\", appearing in 90.00% of analyzed documents.",
  "processingTime": 3000
}
```

Journal request:

```json
{
  "query": "deep learning journal papers",
  "limit": 10,
  "analysisType": "JOURNAL"
}
```

Journal response:

```json
{
  "analysisType": "JOURNAL",
  "totalDocuments": 10,
  "documentsAnalyzed": 10,
  "features": [],
  "headings": [
    {
      "name": "Abstract"
    }
  ],
  "rankedFeatures": [],
  "rankedHeadings": [
    {
      "heading": "Abstract",
      "frequency": 10,
      "percentage": 100,
      "rank": 1
    }
  ],
  "summary": "10 documents were analyzed. The most common journal heading was \"Abstract\", appearing in 100.00% of analyzed documents.",
  "processingTime": 3000
}
```

---

## TypeScript Types

Create:

```text
src/types/api.ts
```

Recommended types:

```ts
export type AnalysisType = "CRIME" | "JOURNAL";

export interface AnalysisRequest {
  query: string;
  limit: number;
  analysisType: AnalysisType;
}

export interface SearchRequest {
  query: string;
  limit: number;
}

export interface SearchResult {
  id: string;
  title: string;
  url: string;
  snippet: string;
  source: string;
}

export interface RankedFeature {
  featureName: string;
  frequency: number;
  percentage: number;
  rank: number;
}

export interface RankedHeading {
  heading: string;
  frequency: number;
  percentage: number;
  rank: number;
}

export interface ExtractedItem {
  name: string;
}

export interface AnalysisResponse {
  analysisType: AnalysisType;
  totalDocuments: number;
  documentsAnalyzed: number;
  features: ExtractedItem[];
  headings: ExtractedItem[];
  rankedFeatures: RankedFeature[];
  rankedHeadings: RankedHeading[];
  summary: string;
  processingTime: number;
}

export interface CrawlRequest {
  urls: string[];
}

export interface CrawlResponse {
  totalPages: number;
  successfulPages: number;
  failedPages: number;
  processingTime: number;
}
```

---

## State Management

Use React hooks.

Manage:

```text
query
analysisType
limit
serpResults
analysisResponse
rankedFeatures
rankedHeadings
summary
statistics
loading
error
backendStatus
```

Example flow:

```text
User input
-> useState
-> API request
-> update state
-> render dashboard
```

---

## Rendering Rules

When `analysisType` is `CRIME`:

- Show `rankedFeatures`.
- Hide or de-emphasize `rankedHeadings`.
- Bar/pie charts should use `featureName` as the label.

When `analysisType` is `JOURNAL`:

- Show `rankedHeadings`.
- Hide or de-emphasize `rankedFeatures`.
- Bar/pie charts should use `heading` as the label.

Use `frequency` for bar chart values.

Use `percentage` for pie chart values and table percentage display.

---

## User Experience Requirements

Loading state:

```text
Analyzing documents...
Processing SERP results...
```

Error state:

```text
Unable to connect to backend. Please try again.
```

Empty state:

```text
Enter a query to start analysis.
```

Backend offline state:

```text
Backend unavailable
```

---

## Styling Requirements

Create a modern academic dashboard.

Requirements:

- Responsive layout.
- Clean analytics cards.
- Ranked tables that are easy to scan.
- Charts sized for desktop and mobile.
- Research dashboard style.
- Good spacing.
- Mobile compatible.

Suggested sections:

```text
Header
Search controls
Summary cards
Charts
Ranking tables
SERP results
```

---

## Development Order

### Phase A - Project Setup

- Install dependencies.
- Configure Tailwind.
- Create folder structure.
- Add TypeScript API types.

### Phase B - API Integration

- Setup Axios.
- Connect to Spring Boot.
- Test backend status.
- Implement `analysisService`.

### Phase C - Core UI

- Navbar.
- Dashboard.
- SearchPanel.
- StatCard.
- SummaryPanel.
- ResultCard.
- FeatureTable.
- HeadingTable.

### Phase D - Backend Integration

- Connect SearchPanel to `/api/analyze`.
- Store `AnalysisResponse`.
- Render `rankedFeatures` for CRIME.
- Render `rankedHeadings` for JOURNAL.
- Render `summary`.

### Phase E - Visualization

- Bar chart for frequency.
- Pie chart for percentage.
- Summary/stat cards.

---

## Final Application Workflow

```text
User opens dashboard
-> frontend checks /api/status
-> user enters research query
-> user selects CRIME or JOURNAL
-> user clicks Analyze
-> React sends POST /api/analyze
-> Spring Boot processes SERP/crawl/extraction/ranking
-> React receives ranked report
-> dashboard displays:
   - summary
   - stats
   - ranked features or headings
   - charts
   - optional SERP results
```

---

## Important Backend Alignment Notes

- The backend currently uses a mock SERP provider.
- `/api/analyze` is the main endpoint for the final dashboard.
- `/api/search` can be used to display SERP cards separately.
- `/api/crawl` is available for crawler testing or future progress features.
- The final assignment visualization should be built from `rankedFeatures` and `rankedHeadings`, not from the older `features` array alone.
