# Phase 3 Implementation Prompt — SERP Search Integration Layer

```markdown
# Phase 3 Implementation Prompt — SERP Search Integration

## Project Context

You are building a Multithreaded Semantic SERP Analyzer.

Current stack:

Backend:
- Java 26
- Spring Boot 3.x
- Maven

Frontend:
- React + Vite

Completed:

Phase 1:
✓ Spring Boot setup
✓ Maven configuration
✓ Thread pool foundation
✓ Project structure

Phase 2:
✓ DTO layer
✓ Domain models
✓ REST controllers
✓ Service architecture
✓ Validation
✓ Exception handling

Phase 3 focuses on implementing the SERP retrieval layer.

The goal is to accept a user's search query and retrieve structured Search Engine Results Page (SERP) data that will later be processed by the crawler and analysis engines.

---

# Phase 3 Objective

Implement a SERP search module that:

1. Receives a search query.
2. Retrieves relevant search results.
3. Converts results into internal models.
4. Returns structured SERP results.
5. Prepares data for concurrent crawling.

---

# Phase 3 Architecture

The architecture becomes:

```

React Frontend

```
   |
   v
```

AnalysisController

```
   |
   v
```

AnalysisService

```
   |
   v
```

SearchService

```
   |
   v
```

Search Provider API

```
   |
   v
```

SearchResult Objects

```
   |
   v
```

Crawler Engine (Phase 4)

```

---

# Important Design Decision

Do not directly connect the controller to a search API.

Wrong:

```

Controller
|
|
Google API

```

Correct:

```

Controller

```
 |
```

Service

```
 |
```

Search Provider Interface

```
 |
```

Implementation

```

This allows changing search providers later.

---

# Package Additions

Create:

```

search

```

inside:

```

com.serpanalyzer.serp_analyzer_backend

```

Final structure:

```

serp_analyzer_backend

├── controller
├── service
├── dto
├── model
├── search
│
│── SearchProvider.java
│── SearchService.java
│── SearchProviderImpl.java
│
├── parser
├── analysis
├── concurrency
└── config

```

---

# Task 1 — Create Search Provider Interface

Create:

```

search/SearchProvider.java

```

Purpose:

Defines how the application retrieves SERP results.

Interface:

Methods:

```

List<SearchResult> search(String query, int limit)

```

Example:

Input:

```

"crime reporting papers"

```

Output:

```

[
SearchResult,
SearchResult,
SearchResult
]

```

---

# Task 2 — Create Search Service

Create:

```

search/SearchService.java

```

Responsibilities:

- Receive search requests.
- Validate query.
- Call SearchProvider.
- Return search results.

Flow:

```

SearchService

```
   |
```

SearchProvider

```
   |
```

Results

```

Implement:

```

search(String query, int limit)

```

Return:

```

List<SearchResult>

```

---

# Task 3 — Implement Mock SERP Provider

For initial development, create a mock provider.

Create:

```

search/MockSearchProvider.java

```

Purpose:

Simulate SERP results before integrating a real search API.

Example output:

```

Crime Analysis Report
[https://example.com/crime1](https://example.com/crime1)

AI Based Crime Detection
[https://example.com/crime2](https://example.com/crime2)

Deep Learning Crime Prediction
[https://example.com/crime3](https://example.com/crime3)

```

Requirements:

- Implement SearchProvider interface.
- Return SearchResult objects.
- Support limit parameter.

---

# Task 4 — Create Search Configuration

Create:

```

config/SearchConfig.java

```

Configure the search provider as a Spring Bean.

Example:

```

@Bean

SearchProvider searchProvider()

```

Purpose:

Allows switching:

Current:

```

MockSearchProvider

```

Future:

```

GoogleSearchProvider
BingSearchProvider
SemanticScholarProvider

```

---

# Task 5 — Update SearchResult Model

Update:

```

model/SearchResult.java

```

Add:

Fields:

```

id
title
url
snippet
source

```

Example:

```

id:
1

title:
Crime Reporting System Analysis

url:
[https://example.com](https://example.com)

source:
Google

```

---

# Task 6 — Create SERP Controller

Create:

```

controller/SearchController.java

```

Endpoint:

```

POST /api/search

````

Request:

```json
{
    "query":"crime reporting papers",
    "limit":10
}
````

Response:

```json
[
 {
  "title":"Crime Report Analysis",
  "url":"https://example.com",
  "snippet":"..."
 }
]
```

Requirements:

* Use SearchService.
* Validate request.
* Return JSON response.

---

# Task 7 — Create Search Request DTO

Create:

```
dto/SearchRequest.java
```

Fields:

```
query
limit
```

Validation:

```
query != empty

limit > 0
```

Example:

```json
{
"query":"deep learning models",
"limit":10
}
```

---

# Task 8 — Connect SearchService to AnalysisService

Update:

```
AnalysisService.java
```

Current:

```
analyze(query)
```

Change flow:

```
AnalysisService

       |

SearchService

       |

SERP Results

       |

Return count
```

Initial response:

```json
{
"totalDocuments":10,
"features":[],
"processingTime":500
}
```

---

# Task 9 — Add Search Exception Handling

Create:

```
exception/SearchException.java
```

Handle:

* Empty query
* Search provider failure
* Invalid limit

Example:

```
Search service unavailable
```

---

# Task 10 — Testing Requirements

Create tests for:

## SearchService Test

Verify:

Input:

```
crime papers
```

Output:

```
List<SearchResult>
```

---

## Search Controller Test

Endpoint:

```
POST /api/search
```

Expected:

```
HTTP 200
```

Response:

```
JSON Array
```

---

# Task 11 — API Documentation

Available APIs after Phase 3:

## Status

```
GET /api/status
```

---

## Search

```
POST /api/search
```

Request:

```json
{
"query":"crime reporting systems",
"limit":10
}
```

Response:

```json
[
 {
"title":"Crime Detection Using AI",
"url":"...",
"snippet":"..."
 }
]
```

---

## Analyze

```
POST /api/analyze
```

Flow:

```
Query

 |

SearchService

 |

SERP Results

 |

Analysis Engine
```

---

# Phase 3 Completion Criteria

The phase is complete when:

Backend:

✓ SearchProvider interface exists

✓ SearchService implemented

✓ Mock SERP provider working

✓ Search API created

✓ SearchResult model completed

✓ Search requests validated

✓ AnalysisService can request SERP data

✓ Unit tests pass

---