Got you. You need the **implementation prompt**, not the code. Here is the complete React frontend implementation prompt in clean Markdown.

```markdown
# React Frontend Implementation Prompt — SERP Analyzer Dashboard

## Project Context

You are implementing the frontend for a Multithreaded Semantic SERP Analyzer system.

The backend is built with:

- Java 26
- Spring Boot 3.x
- REST APIs

The frontend will be built with:

- React
- Vite
- JavaScript
- Axios
- Recharts
- Tailwind CSS

The application analyzes Search Engine Results Pages (SERP) and extracts:

1. Distinctive features from crime-reporting papers.
2. Distinctive headings from deep learning journal papers.
3. Frequency ranking of extracted features.
4. Visualization of analysis results.

---

# Frontend Goal

Build a professional research analytics dashboard that allows users to:

- Submit search queries.
- Select analysis category.
- Send requests to Spring Boot APIs.
- View SERP results.
- View extracted features.
- View frequency rankings.
- Visualize results using charts.

The frontend should communicate with:

```

React Frontend
|
|
v
Spring Boot Backend
|
|
v
SERP Analysis Engine

```

---

# Application Architecture

Implement the frontend using a component-based architecture:

```

src

├── api
│
├── components
│
├── pages
│
├── services
│
├── charts
│
├── hooks
│
├── utils
│
└── App.jsx

```

---

# Required Technologies

## Core

Use:

- React
- Vite


## API Communication

Use:

- Axios


## Routing

Use:

- React Router


## Visualization

Use:

- Recharts


## Styling

Use:

- Tailwind CSS


---

# Main Application Layout

The application should contain:

```

---

SERP Analyzer Dashboard

---

Navigation Bar

---

Search and Analysis Panel

---

Analysis Summary Cards

---

Feature Visualization

---

Feature Ranking Table

---

SERP Search Results

---

```

---

# Pages

Create:

```

src/pages

```

---

## Dashboard Page

File:

```

Dashboard.jsx

```

Purpose:

Main application workspace.

Responsibilities:

- Display search form.
- Trigger analysis.
- Display returned results.
- Display charts.
- Display statistics.

The dashboard should contain:

```

Dashboard

|
|
+-- Search Panel
|
+-- Statistics Cards
|
+-- Feature Charts
|
+-- Feature Table
|
+-- SERP Results

```

---

# Components

Create:

```

src/components

```

---

# 1. Navbar Component

File:

```

Navbar.jsx

```

Responsibilities:

Display:

- Application name.
- Navigation links.
- Backend connection status.

Example:

```

SERP Analyzer

Dashboard
Analysis
Results

```

---

# 2. Search Panel Component

File:

```

SearchPanel.jsx

```

Purpose:

Collect user input.

Fields:

```

Search Query

Analysis Type

Number of Results

Analyze Button

```

Example:

Input:

```

Crime reporting systems

```

Analysis type:

```

Crime Papers

```

or

```

Deep Learning Papers

```

When submitted:

Send request to backend.

---

# 3. Statistics Card Component

File:

```

StatCard.jsx

```

Display:

- Number of documents analyzed.
- Number of extracted features.
- Processing time.
- Number of SERP results.

Example:

```

Documents

10

```
```

Features

25

```

---

# 4. SERP Result Card

File:

```

ResultCard.jsx

```

Display:

```

Title

URL

Description/Snippet

```

Example:

```

Crime Detection Using AI

[https://example.com](https://example.com)

Research paper describing...

```

---

# 5. Feature Table

File:

```

FeatureTable.jsx

```

Display extracted features.

Structure:

| Feature | Frequency |
|---|---|
| Crime Location | 50 |
| Victim Information | 40 |
| Police Statement | 30 |

Sort:

Highest frequency first.

---

# Visualization Components

Create:

```

src/charts

```

---

# Feature Bar Chart

File:

```

FeatureBarChart.jsx

```

Purpose:

Display frequency ranking.

Example:

```

Crime Location       ████████ 50

Victim Information   ██████ 40

Police Report        █████ 30

```

Use:

Recharts BarChart.

---

# Feature Pie Chart

File:

```

FeaturePieChart.jsx

```

Purpose:

Display feature distribution.

Example:

```

Location       40%

Victim         30%

Police         20%

Other          10%

```

---

# API Layer

Create:

```

src/services

```

---

# API Client

File:

```

api.js

```

Responsibilities:

Configure Axios.

Backend URL:

```

[http://localhost:8080](http://localhost:8080)

```

Example:

```

axios.create({
baseURL:"[http://localhost:8080](http://localhost:8080)"
})

```

---

# Analysis Service

File:

```

analysisService.js

```

Responsibilities:

Handle backend communication.

Functions:

```

analyzeQuery()

searchSERP()

checkBackendStatus()

```

---

# Backend APIs

The frontend should consume:

---

## Backend Status

GET:

```

/api/status

```

Purpose:

Check backend availability.

Response:

```

SERP Analyzer Backend Running

```

---

## Search API

POST:

```

/api/search

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
  "title":"",
  "url":"",
  "snippet":""
 }
]
```

---

## Analysis API

POST:

```
/api/analyze
```

Request:

```json
{
    "query":"crime reporting systems",
    "limit":10,
    "analysisType":"CRIME"
}
```

Response:

```json
{
    "totalDocuments":10,

    "features":[
        {
            "name":"Crime Location",
            "frequency":50
        }
    ],

    "processingTime":3000
}
```

---

# State Management

Use React hooks.

Manage:

```
query

analysisType

results

features

statistics

loading

error
```

Example flow:

```
User Input

      |

useState

      |

API Request

      |

Update State

      |

Render Dashboard
```

---

# User Experience Requirements

## Loading State

During analysis:

Display:

```
Analyzing documents...

Processing SERP results...
```

---

## Error State

If backend fails:

Display:

```
Unable to connect to backend.
Please try again.
```

---

## Empty State

Before analysis:

Display:

```
Enter a query to start analysis.
```

---

# Styling Requirements

Create a modern academic dashboard.

Requirements:

* Responsive layout.
* Clean cards.
* Research dashboard style.
* Good spacing.
* Mobile compatible.

Suggested theme:

```
Header

Sidebar

Analytics Cards

Charts

Tables
```

---

# Development Order

Implement in this order:

## Phase A — Project Setup

Tasks:

* Configure Vite.
* Install dependencies.
* Configure Tailwind.
* Create folder structure.

---

## Phase B — API Integration

Tasks:

* Setup Axios.
* Connect to Spring Boot.
* Test backend status.

---

## Phase C — Core UI

Tasks:

Create:

* Navbar.
* Dashboard.
* Search Panel.
* Result Cards.
* Feature Table.

---

## Phase D — Backend Integration

Tasks:

Connect:

```
Search Panel

        |

Analysis API

        |

Dashboard Update
```

---

## Phase E — Visualization

Implement:

* Bar charts.
* Pie charts.
* Frequency graphs.

---

# Final Application Workflow

The completed application should work as:

```
User opens dashboard

        |

Enters research query

        |

Selects:

Crime Papers

OR

Deep Learning Papers

        |

Clicks Analyze

        |

React sends API request

        |

Spring Boot processes SERP

        |

Results returned

        |

Dashboard displays:

- SERP Results
- Extracted Features
- Rankings
- Charts
- Statistics
```

---

# Future Extensions

Prepare the frontend for:

Phase 4:

* Live crawler progress.
* Thread activity monitoring.
* Processing status.

Phase 5:

* Semantic similarity visualization.
* Document clustering.
* Advanced analytics.

Phase 6:

* Final research dashboard.
* Export reports.

```

This is the frontend equivalent of the backend phase prompts and will keep the React implementation aligned with the Spring Boot architecture.
```
