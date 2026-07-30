import type { RankedHeading } from '../types/api'

interface HeadingTableProps {
  items: RankedHeading[]
}

export function HeadingTable({ items }: HeadingTableProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">Ranked Headings</h2>
        <span className="section__caption">Journal heading frequency</span>
      </div>
      {items.length ? (
        <table className="table">
          <thead>
            <tr>
              <th>Rank</th>
              <th>Heading</th>
              <th>Frequency</th>
              <th>Percentage</th>
            </tr>
          </thead>
          <tbody>
            {items.map((item) => (
              <tr key={item.heading}>
                <td>{item.rank}</td>
                <td>{item.heading}</td>
                <td>{item.frequency}</td>
                <td>{item.percentage.toFixed(2)}%</td>
              </tr>
            ))}
          </tbody>
        </table>
      ) : (
        <div className="state">No ranked headings yet.</div>
      )}
    </section>
  )
}
