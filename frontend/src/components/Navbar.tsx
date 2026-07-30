import type { ReactNode } from 'react'

interface NavbarProps {
  status: 'loading' | 'online' | 'error'
  statusLabel: string
  rightSlot?: ReactNode
}

export function Navbar({ status, statusLabel, rightSlot }: NavbarProps) {
  return (
    <header className="topbar">
      <div className="brand">
        <span className="brand__name">SERP Analyzer</span>
        <span className="brand__meta">Research analytics dashboard</span>
      </div>
      <div style={{ display: 'flex', alignItems: 'center', gap: 12 }}>
        <span className="status-pill">
          <span className={`status-dot is-${status}`} />
          {statusLabel}
        </span>
        {rightSlot}
      </div>
    </header>
  )
}
