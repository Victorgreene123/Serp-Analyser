import {
  Bar,
  BarChart,
  CartesianGrid,
  ResponsiveContainer,
  Tooltip,
  XAxis,
  YAxis,
} from 'recharts'

interface RankingBarChartProps {
  data: Array<{ name: string; frequency: number }>
  title: string
}

export function RankingBarChart({ data, title }: RankingBarChartProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">{title}</h2>
        <span className="section__caption">Sorted by frequency</span>
      </div>
      <div className="chart-box">
        <ResponsiveContainer width="100%" height="100%">
          <BarChart data={data} layout="vertical" margin={{ top: 8, right: 16, left: 8, bottom: 8 }}>
            <CartesianGrid strokeDasharray="3 3" stroke="#e2e8f0" />
            <XAxis type="number" />
            <YAxis type="category" dataKey="name" width={140} />
            <Tooltip />
            <Bar dataKey="frequency" fill="#0f172a" radius={[0, 8, 8, 0]} />
          </BarChart>
        </ResponsiveContainer>
      </div>
    </section>
  )
}
