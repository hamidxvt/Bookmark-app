import { useQuery } from '@tanstack/react-query'
import { dashboardApi } from '../api/client'
import { Users, MapPin, ClipboardCheck, AlertTriangle, Package, Calendar } from 'lucide-react'
import type { DashboardStats } from '../types'

function StatCard({ label, value, sub, icon: Icon, color }: {
  label: string; value: number; sub?: string; icon: React.ElementType; color: string
}) {
  return (
    <div className="bg-white rounded-xl p-5 border border-gray-100">
      <div className="flex items-start justify-between">
        <div>
          <p className="text-sm text-gray-500">{label}</p>
          <p className="text-3xl font-bold text-gray-900 mt-1">{value}</p>
          {sub && <p className="text-xs text-gray-400 mt-0.5">{sub}</p>}
        </div>
        <div className={`p-2.5 rounded-lg ${color}`}>
          <Icon size={20} className="text-white" />
        </div>
      </div>
    </div>
  )
}

export default function DashboardPage() {
  const { data: stats, isLoading } = useQuery<DashboardStats>({
    queryKey: ['dashboard-stats'],
    queryFn: () => dashboardApi.stats().then((r) => r.data),
    refetchInterval: 30000,
  })

  if (isLoading) return <div className="p-8 text-gray-400">Loading dashboard…</div>
  if (!stats) return null

  return (
    <div className="p-8">
      <div className="mb-6">
        <h1 className="text-xl font-bold text-gray-900">Dashboard</h1>
        <p className="text-sm text-gray-500 mt-0.5">
          {new Date().toLocaleDateString('en-PK', { weekday: 'long', day: 'numeric', month: 'long', year: 'numeric' })}
        </p>
      </div>

      <div className="grid grid-cols-2 lg:grid-cols-3 gap-4 mb-8">
        <StatCard label="Officers Online" value={stats.officers_online} sub={`of ${stats.total_officers} total`} icon={Users} color="bg-[#1B4F9B]" />
        <StatCard label="Visits Today" value={stats.visits_today} sub={`${stats.visits_completed_today} completed`} icon={ClipboardCheck} color="bg-green-600" />
        <StatCard label="Pending Reviews" value={stats.pending_missed_reviews} sub="missed visit reasons" icon={AlertTriangle} color="bg-amber-500" />
        <StatCard label="Sample Requests" value={stats.pending_sample_requests} sub="awaiting approval" icon={Package} color="bg-purple-600" />
        <StatCard label="Leave Requests" value={stats.pending_leave_requests} sub="awaiting approval" icon={Calendar} color="bg-sky-500" />
        <StatCard label="Active Officers" value={stats.officers_online} sub="currently in the field" icon={MapPin} color="bg-teal-600" />
      </div>

      <div className="bg-[#1B4F9B]/5 border border-[#1B4F9B]/10 rounded-xl p-4 text-sm text-[#1B4F9B]">
        Live map, visit drill-down, and detailed reports are available in the sidebar navigation.
      </div>
    </div>
  )
}
