import { Cell, Legend, Pie, PieChart, ResponsiveContainer, Tooltip } from 'recharts'

interface RankingPieChartProps {
  data: Array<{ name: string; value: number }>
  title: string
}

const colors = ['#0f172a', '#1d4ed8', '#2563eb', '#7c3aed', '#0f766e', '#b45309']

export function RankingPieChart({ data, title }: RankingPieChartProps) {
  return (
    <section className="panel section">
      <div className="section__header">
        <h2 className="section__title">{title}</h2>
        <span className="section__caption">Share of total frequency</span>
      </div>
      <div className="chart-box chart-box--small">
        <ResponsiveContainer width="100%" height="100%">
          <PieChart>
            <Pie data={data} dataKey="value" nameKey="name" innerRadius={56} outerRadius={96} paddingAngle={4}>
              {data.map((entry, index) => (
                <Cell key={`${entry.name}-${index}`} fill={colors[index % colors.length]} />
              ))}
            </Pie>
            <Tooltip />
            <Legend />
          </PieChart>
        </ResponsiveContainer>
      </div>
    </section>
  )
}
