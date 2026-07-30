import { useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { Activity, FileSearch, BookOpen, ArrowRight } from 'lucide-react'
import { Navbar } from '../components/Navbar'
import { checkBackendStatus } from '../services/analysisService'

export function Home() {
  const [backendStatus, setBackendStatus] = useState<'loading' | 'online' | 'error'>('loading')
  const [statusLabel, setStatusLabel] = useState('Checking backend')

  useEffect(() => {
    let active = true

    checkBackendStatus()
      .then(() => {
        if (!active) return
        setBackendStatus('online')
        setStatusLabel('Backend online')
      })
      .catch(() => {
        if (!active) return
        setBackendStatus('error')
        setStatusLabel('Backend unavailable')
      })

    return () => {
      active = false
    }
  }, [])

  return (
    <div className="app-shell">
      <Navbar status={backendStatus} statusLabel={statusLabel} />

      <main className="container">
        <section className="hero-panel">
          <div className="panel panel--primary">
            <span className="eyebrow">
              <Activity size={14} />
              Multithreaded semantic analysis
            </span>
            <h1 className="title">Analyze SERP results with concurrent crawling and ranking.</h1>
            <p className="subtitle">
              Submit a search query, let the backend crawl documents concurrently, extract semantic
              features, rank them by frequency, and visualize everything in one place.
            </p>

            <div className="hero-actions">
              <Link to="/dashboard" className="button button--primary" style={{ textDecoration: 'none' }}>
                <span style={{ display: 'inline-flex', alignItems: 'center', gap: 8 }}>
                  Open Dashboard
                  <ArrowRight size={16} />
                </span>
              </Link>
              <Link to="/crime" className="button button--secondary" style={{ textDecoration: 'none' }}>
                Crime Papers
              </Link>
              <Link to="/journal" className="button button--secondary" style={{ textDecoration: 'none' }}>
                Journal Papers
              </Link>
            </div>
          </div>

          <div className="panel panel--secondary hero-art">
            <div className="mini-stat">
              <div className="mini-stat__label">Backend</div>
              <div className="mini-stat__value" style={{ fontSize: 20 }}>
                {backendStatus === 'online' ? 'Online' : backendStatus === 'loading' ? '...' : 'Offline'}
              </div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">Architecture</div>
              <div className="mini-stat__value" style={{ fontSize: 20 }}>Spring Boot</div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">Concurrency</div>
              <div className="mini-stat__value" style={{ fontSize: 20 }}>Thread Pool</div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">Frontend</div>
              <div className="mini-stat__value" style={{ fontSize: 20 }}>React + TS</div>
            </div>
          </div>
        </section>

        <div className="grid" style={{ marginTop: 20 }}>
          <section className="grid grid--two">
            <Link to="/crime" className="panel section module-card" style={{ textDecoration: 'none', color: 'inherit' }}>
              <div className="section__header">
                <h2 className="section__title">
                  <span style={{ display: 'inline-flex', alignItems: 'center', gap: 10 }}>
                    <FileSearch size={20} />
                    Crime Reporting Analyzer
                  </span>
                </h2>
              </div>
              <p className="subtitle" style={{ margin: 0 }}>
                Extract distinctive features from crime-reporting papers — headlines, locations, suspects,
                police statements, court proceedings, and more. Rank them by how frequently they appear
                across SERP results.
              </p>
              <div className="chips" style={{ marginTop: 16 }}>
                <span className="chip">Headline</span>
                <span className="chip">Crime Type</span>
                <span className="chip">Location</span>
                <span className="chip">Suspect</span>
                <span className="chip">Evidence</span>
                <span className="chip">Arrest</span>
              </div>
              <div className="footer-note" style={{ display: 'flex', alignItems: 'center', gap: 6 }}>
                Open Crime Analysis <ArrowRight size={12} />
              </div>
            </Link>

            <Link to="/journal" className="panel section module-card" style={{ textDecoration: 'none', color: 'inherit' }}>
              <div className="section__header">
                <h2 className="section__title">
                  <span style={{ display: 'inline-flex', alignItems: 'center', gap: 10 }}>
                    <BookOpen size={20} />
                    Journal Heading Analyzer
                  </span>
                </h2>
              </div>
              <p className="subtitle" style={{ margin: 0 }}>
                Extract common sub-headings from deep learning journal papers — abstract, methodology,
                dataset, results, and more. See which structural sections appear most often across the
                literature.
              </p>
              <div className="chips" style={{ marginTop: 16 }}>
                <span className="chip">Abstract</span>
                <span className="chip">Introduction</span>
                <span className="chip">Methodology</span>
                <span className="chip">Results</span>
                <span className="chip">Discussion</span>
                <span className="chip">Conclusion</span>
              </div>
              <div className="footer-note" style={{ display: 'flex', alignItems: 'center', gap: 6 }}>
                Open Journal Analysis <ArrowRight size={12} />
              </div>
            </Link>
          </section>

          <section className="panel section">
            <div className="section__header">
              <h2 className="section__title">How It Works</h2>
              <span className="section__caption">Powered by Java concurrency</span>
            </div>
            <div className="cards">
              <article className="card">
                <div className="card__label">Step 1</div>
                <div className="card__value" style={{ fontSize: 18 }}>Search</div>
                <div className="card__subtext">Submit a query and retrieve SERP results from the search service</div>
              </article>
              <article className="card">
                <div className="card__label">Step 2</div>
                <div className="card__value" style={{ fontSize: 18 }}>Crawl</div>
                <div className="card__subtext">Worker threads download pages concurrently via ExecutorService</div>
              </article>
              <article className="card">
                <div className="card__label">Step 3</div>
                <div className="card__value" style={{ fontSize: 18 }}>Parse</div>
                <div className="card__subtext">HTML and PDF content is cleaned and normalized into structured text</div>
              </article>
              <article className="card">
                <div className="card__label">Step 4</div>
                <div className="card__value" style={{ fontSize: 18 }}>Extract</div>
                <div className="card__subtext">Features or headings are extracted using keyword matching and NLP</div>
              </article>
              <article className="card">
                <div className="card__label">Step 5</div>
                <div className="card__value" style={{ fontSize: 18 }}>Rank</div>
                <div className="card__subtext">ConcurrentHashMap aggregates counts; results are ranked and categorized</div>
              </article>
            </div>
          </section>
        </div>
      </main>
    </div>
  )
}
