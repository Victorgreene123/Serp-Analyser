import { Search } from 'lucide-react'
import type { AnalysisType } from '../types/api'

interface SearchPanelProps {
  query: string
  analysisType: AnalysisType
  limit: number
  onQueryChange: (value: string) => void
  onAnalysisTypeChange: (value: AnalysisType) => void
  onLimitChange: (value: number) => void
  onSubmit: () => void
  loading: boolean
}

export function SearchPanel({
  query,
  analysisType,
  limit,
  onQueryChange,
  onAnalysisTypeChange,
  onLimitChange,
  onSubmit,
  loading,
}: SearchPanelProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">Search and Analysis</h2>
        <span className="section__caption">Use the backend analysis pipeline</span>
      </div>
      <div className="form">
        <input
          className="field"
          value={query}
          onChange={(event) => onQueryChange(event.target.value)}
          placeholder="Crime reporting systems, deep learning journal papers..."
          aria-label="Search query"
        />
        <select
          className="select"
          value={analysisType}
          onChange={(event) => onAnalysisTypeChange(event.target.value as AnalysisType)}
          aria-label="Analysis type"
        >
          <option value="CRIME">Crime Papers</option>
          <option value="JOURNAL">Deep Learning Papers</option>
        </select>
        <input
          className="field"
          type="number"
          min={1}
          value={limit}
          onChange={(event) => onLimitChange(Number(event.target.value))}
          aria-label="Number of results"
        />
        <button className="button button--primary" type="button" onClick={onSubmit} disabled={loading}>
          <span style={{ display: 'inline-flex', alignItems: 'center', gap: 8 }}>
            <Search size={16} />
            {loading ? 'Analyzing' : 'Analyze'}
          </span>
        </button>
      </div>
    </section>
  )
}
