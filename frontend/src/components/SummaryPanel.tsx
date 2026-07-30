interface SummaryPanelProps {
  summary: string
}

export function SummaryPanel({ summary }: SummaryPanelProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">Analysis Summary</h2>
        <span className="section__caption">Backend-generated narrative</span>
      </div>
      <div className="summary">{summary || 'Enter a query to start analysis.'}</div>
    </section>
  )
}
