# Semantic SERP Analyzer

A multithreaded Semantic Search Engine Results Page (SERP) Analyzer built with **Spring Boot** (backend) and **React + TypeScript** (frontend) for a Java Concurrency course.

The system extracts and ranks distinctive features from crime-reporting papers and common sub-headings from deep learning journal papers using concurrent crawling and semantic analysis.

---

## Prerequisites

Before you begin, make sure you have the following installed:

| Tool | Version | Check |
|------|---------|-------|
| Java JDK | 21+ | `java --version` |
| Maven | 3.9+ | `mvn --version` |
| Node.js | 18+ | `node --version` |
| npm | 9+ | `npm --version` |
| Git | any | `git --version` |

---

## Project Structure

```
Serp-Analyser/
├── serp-analyzer-backend/     # Spring Boot REST API
│   ├── src/main/java/com/serpanalyzer/
│   │   ├── config/            # Thread pool & CORS config
│   │   ├── controller/        # REST endpoints
│   │   ├── service/           # Core analysis orchestration
│   │   ├── crawler/           # Concurrent web crawling
│   │   ├── parser/            # HTML/PDF parsing
│   │   ├── extractor/         # Feature & heading extraction
│   │   ├── ranking/           # Frequency ranking engine
│   │   ├── search/            # SERP search provider
│   │   ├── nlp/               # Keyword matching
│   │   ├── model/             # Domain models
│   │   ├── dto/               # Request/response DTOs
│   │   └── exception/         # Error handling
│   └── src/test/              # Unit tests
│
├── frontend/                  # React + Vite + TypeScript
│   └── src/
│       ├── api/               # Axios client
│       ├── charts/            # Recharts visualizations
│       ├── components/        # Reusable UI components
│       ├── pages/             # Route pages
│       ├── services/          # API service functions
│       ├── types/             # TypeScript interfaces
│       └── App.tsx            # Router entry point
│
├── Agent.md                   # Full implementation plan
├── Frontend.md                # Frontend specification
├── Phase2.md – Phase-6.md     # Phase-by-phase implementation prompts
└── README.md                  # This file
```

---

## Setup & Running

### 1. Clone the Repository

```bash
git clone <your-repo-url>
cd Serp-Analyser
```

### 2. Start the Backend

```bash
cd serp-analyzer-backend

# Build the project
./mvnw clean install

# Run the Spring Boot application
./mvnw spring-boot:run
```

The backend starts on **http://localhost:8080**.

Verify it's running:

```bash
curl http://localhost:8080/api/status
# Should return: SERP Analyzer Backend Running
```

> **Windows users:** Use `mvnw.cmd` instead of `./mvnw`.

### 3. Start the Frontend

Open a **new terminal** (keep the backend running):

```bash
cd frontend

# Install dependencies
npm install

# Start the dev server
npm run dev
```

The frontend starts on **http://localhost:5173** (Vite default).

### 4. Open in Browser

Go to **http://localhost:5173** — you should see the Home page with the backend status showing "Online".

---

## Pages & Navigation

| Route | Page | Description |
|-------|------|-------------|
| `/` | Home | Landing page with module overview and navigation |
| `/dashboard` | Dashboard | Combined view — toggle between CRIME and JOURNAL analysis |
| `/crime` | Crime Analysis | Dedicated crime-reporting feature analysis |
| `/journal` | Journal Analysis | Dedicated journal heading analysis |

---

## Backend API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/status` | Health check |
| `POST` | `/api/search` | Search SERP results |
| `POST` | `/api/crawl` | Crawl a list of URLs |
| `POST` | `/api/analyze` | Full analysis pipeline |

### Example: Analyze Crime Papers

```bash
curl -X POST http://localhost:8080/api/analyze \
  -H "Content-Type: application/json" \
  -d '{"query": "crime reporting systems", "limit": 10, "analysisType": "CRIME"}'
```

### Example: Analyze Journal Papers

```bash
curl -X POST http://localhost:8080/api/analyze \
  -H "Content-Type: application/json" \
  -d '{"query": "deep learning journal papers", "limit": 10, "analysisType": "JOURNAL"}'
```

---

## Technology Stack

### Backend
- Java 21, Spring Boot 3, Maven
- Jsoup (HTML parsing), Apache PDFBox (PDF parsing)
- Java Concurrency: `ExecutorService`, `CompletableFuture`, `ConcurrentHashMap`, `AtomicInteger`

### Frontend
- React 19, Vite, TypeScript
- Axios (HTTP client)
- Recharts (charts)
- Lucide React (icons)
- React Router (routing)

---

## Concurrency Highlights

The backend uses Java's concurrency framework extensively:

- **ThreadPoolExecutor** manages a pool of worker threads for concurrent page downloads.
- **Callable / Future** pattern lets each URL download run as an independent task.
- **ConcurrentHashMap + AtomicInteger** aggregates feature/heading counts in a thread-safe way.
- **ExecutorCompletionService** processes results as they complete, not in submission order.

---

## Troubleshooting

| Problem | Solution |
|---------|----------|
| Backend won't start | Check Java version (`java --version` — needs 21+). Make sure port 8080 is free. |
| Frontend shows "Backend unavailable" | Make sure the backend is running on port 8080 first. |
| `npm install` fails | Delete `node_modules` and `package-lock.json`, then run `npm install` again. |
| CORS errors in browser console | The backend's `CorsConfig.java` should allow `http://localhost:5173`. |
| Port conflicts | Backend: change `server.port` in `application.properties`. Frontend: Vite auto-picks the next free port. |
