import type { RankedFeature } from '../types/api'

interface FeatureTableProps {
  items: RankedFeature[]
}

export function FeatureTable({ items }: FeatureTableProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">Ranked Features</h2>
        <span className="section__caption">Crime-reporting feature frequency</span>
      </div>
      {items.length ? (
        <table className="table">
          <thead>
            <tr>
              <th>Rank</th>
              <th>Feature</th>
              <th>Frequency</th>
              <th>Percentage</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.featureName}>
                <td>{item.rank}</td>
                <td>{item.featureName}</td>
                <td>{item.frequency}</td>
                <td>{item.percentage.toFixed(2)}%</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <div className="state">No ranked features yet.</div>
      )}
    </section>
  )
}
