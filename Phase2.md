# Phase 2 Implementation Prompt — API Design and Domain Layer

## Project Context

You are building a Multithreaded Semantic SERP Analyzer using:

Backend:
- Java 26
- Spring Boot 3.x
- Maven

Frontend:
- React + Vite

The system analyzes Search Engine Results Pages (SERP), extracts information from crime-reporting papers and deep learning journal papers, identifies common features/headings, ranks them by frequency, and visualizes the results.

Phase 2 focuses on establishing the backend architecture, API contracts, domain models, and service layer before implementing crawling and concurrency.

---

# Phase 2 Objective

Implement a clean Spring Boot backend architecture that supports:

1. Receiving analysis requests from the React frontend.
2. Returning structured analysis results.
3. Separating API, business logic, and domain models.
4. Preparing the backend for future:
   - SERP retrieval
   - Web crawling
   - Document parsing
   - Feature extraction
   - Concurrent processing

---

# Architecture Requirement

Implement the following layered architecture:

```
Frontend (React)
        |
        |
        v
REST Controller Layer
        |
        |
        v
Service Layer
        |
        |
        v
Analysis Engine
        |
        |
        v
Concurrency Layer
        |
        |
        v
Data Processing
```

Follow Spring Boot best practices:

- Controllers handle HTTP communication.
- Services contain business logic.
- DTOs handle API communication.
- Models represent domain objects.
- Configuration manages application components.

---

# Package Structure

Create the following package structure:

```
com.serpanalyzer.serp_analyzer_backend

│
├── controller
│
├── service
│
├── dto
│
├── model
│
├── config
│
├── concurrency
│
├── parser
│
├── analysis
│
├── exception
│
└── util
```

---

# Task 1 — Create API Request DTOs

Create:

```
dto/AnalysisRequest.java
```

Purpose:

Represents frontend requests.

Fields:

```
query
limit
analysisType
```

Example request:

```json
{
    "query": "crime reporting papers",
    "limit": 10,
    "analysisType": "CRIME"
}
```

Requirements:

- Add validation annotations.
- Query cannot be empty.
- Limit must be greater than zero.
- Analysis type must be provided.

Use:

```
jakarta.validation
```

---

# Task 2 — Create Search Result DTO

Create:

```
dto/SearchResultDTO.java
```

Represents SERP search results.

Fields:

```
title
url
snippet
```

Example:

```json
{
"title":"Crime Report Analysis",
"url":"https://example.com",
"snippet":"content preview..."
}
```

Requirements:

- Include constructors.
- Include getters.
- Prepare for JSON serialization.

---

# Task 3 — Create Analysis Response DTO

Create:

```
dto/AnalysisResponse.java
```

Purpose:

Returned back to React frontend.

Fields:

```
totalDocuments
features
processingTime
```

Example:

```json
{
    "totalDocuments":20,
    "features":[
        "Police Statement",
        "Crime Location",
        "Victim Information"
    ],
    "processingTime":3500
}
```

Requirements:

Support future visualization requirements.

---

# Task 4 — Create Domain Models

Create:

```
model/SearchResult.java
```

Represents a SERP result internally.

Fields:

```
title
url
snippet
```

---

Create:

```
model/PageContent.java
```

Represents crawled documents.

Fields:

```
url
content
title
wordCount
```

Purpose:

Will later store extracted webpage/PDF text.

---

Create:

```
model/Feature.java
```

Represents extracted crime-reporting features.

Fields:

```
name
frequency
category
```

Example:

```
Feature:

name:
Police Statement

frequency:
45

category:
COMMON
```

---

Create:

```
model/JournalHeading.java
```

Represents deep learning paper sections.

Fields:

```
heading
frequency
```

Example:

```
Methodology : 50
Dataset : 43
Results : 39
```

---

# Task 5 — Create Service Layer

Create:

```
service/AnalysisService.java
```

Responsibilities:

- Receive analysis requests.
- Coordinate future processing.
- Return AnalysisResponse.

Initial implementation:

```
analyze(query)
```

returns a placeholder response.

Example:

```json
{
"totalDocuments":0,
"features":[],
"processingTime":0
}
```

The service will later call:

```
SearchService

CrawlerService

ParserService

FeatureExtractor

ConcurrentAnalysisEngine
```

---

# Task 6 — Create Analysis Controller

Create:

```
controller/AnalysisController.java
```

Endpoint:

```
POST /api/analyze
```

Consumes:

```json
{
"query":"crime reports",
"limit":10,
"analysisType":"CRIME"
}
```

Produces:

```json
{
"totalDocuments":0,
"features":[],
"processingTime":0
}
```

Requirements:

- Use @RestController.
- Use @RequestMapping.
- Validate incoming requests.
- Inject AnalysisService using constructor injection.

---

# Task 7 — Create API Status Endpoint

Create:

```
controller/StatusController.java
```

Endpoint:

```
GET /api/status
```

Response:

```
SERP Analyzer Backend Running
```

Purpose:

Used by React frontend to verify backend availability.

---

# Task 8 — Configure Application Properties

Update:

```
application.properties
```

Add:

```properties
spring.application.name=serp-analyzer-backend

server.port=8080

app.thread.pool.size=10

logging.level.com.serpanalyzer=DEBUG
```

---

# Task 9 — Add Global Exception Handling

Create:

```
exception/GlobalExceptionHandler.java
```

Handle:

- Invalid requests.
- Validation failures.
- Internal server errors.

Return:

```json
{
"message":"Invalid request",
"timestamp":"2026-07-30T12:00:00"
}
```

Use:

```
@ControllerAdvice
```

---

# Task 10 — Testing Requirements

Create tests for:

## Controller Test

Verify:

```
POST /api/analyze
```

returns:

```
200 OK
```

---

## Service Test

Verify:

```
AnalysisService.analyze()
```

returns:

```
AnalysisResponse
```

---

# Task 11 — API Documentation Preparation

Prepare endpoints:

## Health Check

```
GET /api/status
```

---

## Analysis Endpoint

```
POST /api/analyze
```

Request:

```json
{
"query":"deep learning papers",
"limit":10,
"analysisType":"JOURNAL"
}
```

Response:

```json
{
"totalDocuments":10,
"features":[
"Methodology",
"Dataset",
"Architecture"
],
"processingTime":2300
}
```

---

# Phase 2 Completion Criteria

Phase 2 is complete when:

## Backend

✓ Package architecture created

✓ DTO layer implemented

✓ Domain models created

✓ Controller layer implemented

✓ Service layer implemented

✓ Validation enabled

✓ Exception handling added

✓ REST APIs tested


## Available APIs

```
GET /api/status

POST /api/analyze
```


## Prepared For Next Phase

The system should now be ready for:

Phase 3:

SERP Search Integration

Implementation:

```
SearchService

       |

Search Provider API

       |

List<SearchResult>

```

Phase 4:

Concurrent Crawling Engine

Implementation:

```
ExecutorService

       |

Crawler Tasks

       |

PageContent Results
```
