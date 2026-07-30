interface StatCardProps {
  label: string
  value: string | number
  subtext?: string
}

export function StatCard({ label, value, subtext }: StatCardProps) {
  return (
    <article className="card">
      <div className="card__label">{label}</div>
      <div className="card__value">{value}</div>
      {subtext ? <div className="card__subtext">{subtext}</div> : null}
    </article>
  )
}
