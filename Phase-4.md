# Phase 4 — Multithreaded Web Crawling Engine Implementation Plan

## Overview

Phase 4 introduces the core **Java Concurrency component** of the SERP Analyzer backend.

The goal is to process multiple SERP results simultaneously by creating a multithreaded crawling engine that:

- Receives SERP search results.
- Crawls multiple webpages concurrently.
- Extracts webpage content.
- Stores processed documents.
- Prepares data for feature extraction in Phase 5.

This phase demonstrates practical usage of:

- `ExecutorService`
- Thread pools
- `Callable`
- `Future`
- Concurrent task execution
- Thread-safe result collection


---

# Current System State

## Completed Phases

### Phase 1 — Backend Foundation

Completed:

- Spring Boot project setup
- Maven configuration
- Java 26 environment
- Basic project structure
- Thread pool configuration


### Phase 2 — Backend Architecture

Completed:

- DTO layer
- Model layer
- Controller layer
- Service layer
- Validation
- Exception handling


### Phase 3 — SERP Search Integration

Completed:

- Search API
- Search provider abstraction
- Search service
- SERP result model


Current flow:

```
User Query

      |

SearchController

      |

SearchService

      |

SearchProvider

      |

SERP Results

```

---

# Phase 4 Goal

Transform the system from:

```
SERP Results

      |

Single Processing Flow

      |

Documents

```

into:

```
SERP Results

      |

ExecutorService

      |

Multiple Worker Threads

      |

Concurrent Crawling

      |

PageContent Objects

```

---

# New Architecture

After Phase 4:

```
                 React Frontend
                       |
                       |
                       v
              Analysis Controller
                       |
                       |
                       v
              Analysis Service
                       |
                       |
                       v
              Search Service
                       |
                       |
                       v
              SERP Results
                       |
                       |
                       v
             Crawler Service
                       |
                       |
                       v
              ExecutorService
                       |
        --------------------------------
        |              |               |
        v              v               v

    Thread 1       Thread 2        Thread 3

    URL 1          URL 2           URL 3

        |              |               |

        --------------------------------

                       |

                       v

               PageContent Results

```

---

# Package Structure

Add a new crawler package.

Final structure:

```
serp_analyzer_backend

├── controller
│
├── service
│
├── dto
│
├── model
│
├── search
│
├── crawler
│   │
│   ├── CrawlerService.java
│   ├── CrawlerTask.java
│   ├── WebCrawler.java
│   └── CrawlResult.java
│
├── concurrency
│   └── ExecutorConfig.java
│
├── parser
│
├── analysis
│
└── exception

```

---

# Task 1 — Create PageContent Model

## File

```
model/PageContent.java
```

## Purpose

Represents a crawled webpage.

## Fields

```
id

url

title

content

wordCount

crawlTime

status

```

Example:

```json
{
 "url":"https://example.com",
 "title":"Crime Detection Using AI",
 "content":"Full extracted text...",
 "wordCount":5000,
 "crawlTime":1200,
 "status":"SUCCESS"
}
```

---

# Task 2 — Configure Thread Pool

## File

```
config/ExecutorConfig.java
```

## Purpose

Create a reusable thread pool managed by Spring.

The crawler should not create threads manually.

Avoid:

```java
new Thread()
```

Use:

```java
ExecutorService
```

---

## Configuration

Example:

```
Thread Pool Size = 10
```

Meaning:

```
10 URLs

      |

10 Worker Threads

      |

10 Pages Processed

```

---

# Task 3 — Create WebCrawler Component

## File

```
crawler/WebCrawler.java
```

## Responsibility

Handle individual webpage crawling.

Responsibilities:

- Connect to URL.
- Download HTML.
- Extract readable text.
- Create PageContent object.


Method:

```java
PageContent crawl(String url)
```

Example:

Input:

```
https://research-paper.com
```

Output:

```
PageContent
```

---

# Task 4 — Create CrawlerTask

## File

```
crawler/CrawlerTask.java
```

## Purpose

Represent one crawling operation.

Implement:

```java
Callable<PageContent>
```

Why Callable?

Because each task returns a result.

Unlike Runnable:

```
Runnable

execute task

(no return value)

```

Callable:

```
Callable

execute task

(return PageContent)

```

---

## Flow

```
CrawlerTask

      |

      v

WebCrawler

      |

      v

PageContent

```

---

# Task 5 — Create CrawlerService

## File

```
crawler/CrawlerService.java
```

## Responsibility

Manage multiple crawler tasks.

Input:

```
List<SearchResult>
```

Output:

```
List<PageContent>
```

---

## Processing Flow

```
SERP Results


[
 URL 1,
 URL 2,
 URL 3,
 URL 4
]


        |

        v


Create CrawlerTasks


        |

        v


Submit to ExecutorService


        |

        v


Collect Future Results


        |

        v


Return PageContent List

```

---

# Task 6 — Implement Future Handling

Use:

```java
Future<PageContent>
```

Purpose:

Represent unfinished asynchronous work.

Example:

```
Future

  |
  |
  |--- Running
  |
  |
  |--- Completed
          |
          v
     PageContent

```

Handle:

- `ExecutionException`
- `InterruptedException`
- Timeout errors

---

# Task 7 — Create Crawl DTO

## File

```
dto/CrawlResponse.java
```

## Purpose

Return crawl statistics.

Fields:

```
totalPages

successfulPages

failedPages

processingTime

```

Example:

```json
{
 "totalPages":10,
 "successfulPages":9,
 "failedPages":1,
 "processingTime":4500
}
```

---

# Task 8 — Create Crawling API

## File

```
controller/CrawlerController.java
```

Endpoint:

```
POST /api/crawl
```

---

## Request

```json
{
 "urls":[
    "https://example1.com",
    "https://example2.com"
 ]
}
```

---

## Response

```json
{
 "totalPages":2,
 "successfulPages":2,
 "processingTime":1200
}
```

---

# Task 9 — Integrate With Analysis Service

Update:

```
AnalysisService.java
```

New processing pipeline:

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

Feature Extraction
```

---

# Task 10 — Add Thread Logging

Every thread should log activity.

Example:

```
Thread-1 started crawling:
https://example.com/page1


Thread-2 started crawling:
https://example.com/page2


Thread-1 completed

Thread-2 completed

```

Purpose:

During project demonstration, show actual concurrent execution.

---

# Task 11 — Exception Handling

Create:

```
exception/CrawlException.java
```

Handle:

- Invalid URLs
- Connection failures
- Timeout errors
- Parsing failures


Example response:

```json
{
 "error":"Unable to crawl webpage"
}
```

---

# Task 12 — Testing Strategy

## WebCrawler Test

Input:

```
URL
```

Expected:

```
PageContent
```

---

## CrawlerTask Test

Verify:

```
Callable<PageContent>
```

returns:

```
PageContent
```

---

## CrawlerService Test

Input:

```
10 URLs
```

Expected:

```
10 PageContent objects
```

---

## Concurrency Benchmark

Compare:

### Sequential Processing

```
10 URLs

Each takes 2 seconds

Total:

20 seconds

```

---

### Parallel Processing

```
10 Threads

10 URLs

Total:

~2 seconds

```

---

# Phase 4 Completion Checklist

## Backend Implementation

- [ ] PageContent model created
- [ ] Thread pool configured
- [ ] WebCrawler implemented
- [ ] CrawlerTask implemented
- [ ] Callable used
- [ ] Future used
- [ ] CrawlerService implemented
- [ ] Crawl API created
- [ ] Exceptions handled
- [ ] Concurrency tested
- [ ] Thread logs visible


---

# System After Phase 4

The backend now supports:

```
Search Query

      |

SERP Retrieval

      |

Concurrent Crawling

      |

Document Collection

      |

Feature Extraction

```

---

# Next Phase

## Phase 5 — Semantic Feature Extraction Engine

The next phase will implement:

- Document parsing
- Crime feature extraction
- Deep learning paper heading extraction
- Frequency counting
- Ranking algorithm
- Visualization-ready data

Pipeline:

```
PageContent

      |

Parser

      |

Feature Extractor

      |

Frequency Counter

      |

Analysis Results

```