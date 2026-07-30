import { useEffect, useMemo, useState } from 'react'
import { Link } from 'react-router-dom'
import { Activity, AlertTriangle, ArrowLeft, RefreshCcw } from 'lucide-react'
import { Navbar } from '../components/Navbar'
import { SearchPanel } from '../components/SearchPanel'
import { StatCard } from '../components/StatCard'
import { SummaryPanel } from '../components/SummaryPanel'
import { ResultCard } from '../components/ResultCard'
import { FeatureTable } from '../components/FeatureTable'
import { HeadingTable } from '../components/HeadingTable'
import { RankingBarChart } from '../charts/RankingBarChart'
import { RankingPieChart } from '../charts/RankingPieChart'
import {
  analyzeQuery,
  checkBackendStatus,
  searchSERP,
} from '../services/analysisService'
import type { AnalysisResponse, AnalysisType, SearchResult } from '../types/api'

const defaultAnalysis: AnalysisResponse = {
  analysisType: 'CRIME',
  totalDocuments: 0,
  documentsAnalyzed: 0,
  features: [],
  headings: [],
  rankedFeatures: [],
  rankedHeadings: [],
  summary: '',
  processingTime: 0,
}

export function Dashboard() {
  const [query, setQuery] = useState('crime reporting systems')
  const [analysisType, setAnalysisType] = useState<AnalysisType>('CRIME')
  const [limit, setLimit] = useState(10)
  const [analysisResponse, setAnalysisResponse] = useState<AnalysisResponse>(defaultAnalysis)
  const [searchResults, setSearchResults] = useState<SearchResult[]>([])
  const [loading, setLoading] = useState(false)
  const [backendStatus, setBackendStatus] = useState<'loading' | 'online' | 'error'>('loading')
  const [statusLabel, setStatusLabel] = useState('Checking backend')
  const [error, setError] = useState('')

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

  const rankedItems = analysisType === 'CRIME' ? analysisResponse.rankedFeatures : analysisResponse.rankedHeadings
  const chartData = useMemo(
    () =>
      rankedItems.map((item) => ({
        name: 'featureName' in item ? item.featureName : item.heading,
        frequency: item.frequency,
        percentage: item.percentage,
      })),
    [rankedItems],
  )

  async function handleAnalyze() {
    setLoading(true)
    setError('')

    try {
      const [analysis, serpResults] = await Promise.all([
        analyzeQuery({ query, limit, analysisType }),
        searchSERP({ query, limit }),
      ])

      setAnalysisResponse(analysis)
      setSearchResults(serpResults)
      setBackendStatus('online')
      setStatusLabel('Backend online')
    } catch (err) {
      setError('Unable to connect to backend. Please try again.')
      setBackendStatus('error')
      setStatusLabel('Backend unavailable')
      setAnalysisResponse(defaultAnalysis)
      setSearchResults([])
      console.error(err)
    } finally {
      setLoading(false)
    }
  }

  const totalRanked = rankedItems.length
  const topFrequency = rankedItems[0]?.frequency ?? 0
  const topPercentage = rankedItems[0]?.percentage ?? 0

  return (
    <div className="app-shell">
      <Navbar
        status={backendStatus}
        statusLabel={statusLabel}
        rightSlot={
          <div style={{ display: 'flex', alignItems: 'center', gap: 10 }}>
            <Link to="/" className="button button--secondary" style={{ textDecoration: 'none' }}>
              <span style={{ display: 'inline-flex', alignItems: 'center', gap: 6 }}>
                <ArrowLeft size={14} />
                Home
              </span>
            </Link>
            <button className="button button--secondary" type="button" onClick={handleAnalyze} disabled={loading}>
              <span style={{ display: 'inline-flex', alignItems: 'center', gap: 8 }}>
                <RefreshCcw size={16} />
                Refresh
              </span>
            </button>
          </div>
        }
      />

      <main className="container">
        <section className="hero-panel">
          <div className="panel panel--primary">
            <span className="eyebrow">
              <Activity size={14} />
              Semantic SERP dashboard
            </span>
            <h1 className="title">Rank crime-reporting features and journal headings in one view.</h1>
            <p className="subtitle">
              Search SERP results, crawl documents, extract semantic signals, and turn the backend’s ranked analysis
              into a dashboard your users can scan quickly.
            </p>

            <div className="hero-actions">
              <button className="button button--primary" type="button" onClick={handleAnalyze} disabled={loading}>
                {loading ? 'Analyzing documents...' : 'Analyze query'}
              </button>
              <button className="button button--secondary" type="button" onClick={() => setAnalysisType('CRIME')}>
                Crime Papers
              </button>
              <button className="button button--secondary" type="button" onClick={() => setAnalysisType('JOURNAL')}>
                Deep Learning Papers
              </button>
            </div>
          </div>

          <div className="panel panel--secondary hero-art">
            <div className="mini-stat">
              <div className="mini-stat__label">Documents analyzed</div>
              <div className="mini-stat__value">{analysisResponse.documentsAnalyzed}</div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">Top rank</div>
              <div className="mini-stat__value">{rankedItems[0]?.rank ?? 0}</div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">Top percentage</div>
              <div className="mini-stat__value">{topPercentage.toFixed(2)}%</div>
            </div>
            <div className="mini-stat">
              <div className="mini-stat__label">SERP results</div>
              <div className="mini-stat__value">{searchResults.length}</div>
            </div>
          </div>
        </section>

        <div className="grid" style={{ marginTop: 20 }}>
          <SearchPanel
            query={query}
            analysisType={analysisType}
            limit={limit}
            onQueryChange={setQuery}
            onAnalysisTypeChange={setAnalysisType}
            onLimitChange={setLimit}
            onSubmit={handleAnalyze}
            loading={loading}
          />

          {error ? (
            <div className="state" style={{ color: '#b91c1c', display: 'flex', gap: 10, alignItems: 'center' }}>
              <AlertTriangle size={16} />
              <span>{error}</span>
            </div>
          ) : null}

          <section className="cards">
            <StatCard label="Documents" value={analysisResponse.totalDocuments} subtext="SERP documents fetched" />
            <StatCard label="Analyzed" value={analysisResponse.documentsAnalyzed} subtext="Successful crawls" />
            <StatCard label="Processing" value={`${analysisResponse.processingTime} ms`} subtext="Backend round-trip" />
            <StatCard label="Ranked Items" value={totalRanked} subtext="Visible in the current mode" />
            <StatCard label="Top Share" value={`${topPercentage.toFixed(2)}%`} subtext="Most frequent item" />
          </section>

          <SummaryPanel summary={analysisResponse.summary} />

          <div className="grid grid--two">
            <RankingBarChart
              title={analysisType === 'CRIME' ? 'Crime Feature Ranking' : 'Journal Heading Ranking'}
              data={chartData}
            />
            <RankingPieChart
              title={analysisType === 'CRIME' ? 'Crime Feature Distribution' : 'Journal Heading Distribution'}
              data={chartData.map((item) => ({ name: item.name, value: item.frequency }))}
            />
          </div>

          <div className="grid grid--two">
            <FeatureTable items={analysisResponse.rankedFeatures} />
            <HeadingTable items={analysisResponse.rankedHeadings} />
          </div>

          <section className="panel section">
            <div className="section__header">
              <h2 className="section__title">SERP Results</h2>
              <span className="section__caption">{searchResults.length} results returned from /api/search</span>
            </div>
            {searchResults.length ? (
              <div className="result-list">
                {searchResults.map((result) => (
                  <ResultCard key={result.id} result={result} />
                ))}
              </div>
            ) : (
              <div className="state">Enter a query to start analysis.</div>
            )}
          </section>
        </div>
      </main>
    </div>
  )
}
