import type { SearchResult } from '../types/api'

interface ResultCardProps {
  result: SearchResult
}

export function ResultCard({ result }: ResultCardProps) {
  return (
    <article className="result-card">
      <h3 className="result-card__title">{result.title}</h3>
      <div className="result-card__url">{result.url}</div>
      <div className="result-card__snippet">{result.snippet}</div>
      <div className="footer-note">Source: {result.source}</div>
    </article>
  )
}
