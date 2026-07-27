import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { visitsApi } from '../../api/client'
import type { Visit } from '../../types'
import VisitDetailModal from './VisitDetailModal'
import { Search, Filter } from 'lucide-react'

const statusColors: Record<string, string> = {
  pending: 'bg-gray-100 text-gray-700',
  ongoing: 'bg-blue-100 text-blue-700',
  completed: 'bg-green-100 text-green-700',
  missed: 'bg-red-100 text-red-700',
}

export default function VisitsPage() {
  const [selectedVisit, setSelectedVisit] = useState<Visit | null>(null)
  const [search, setSearch] = useState('')
  const [statusFilter, setStatusFilter] = useState('')
  const [dateFilter, setDateFilter] = useState(new Date().toISOString().split('T')[0])

  const { data, isLoading } = useQuery({
    queryKey: ['visits', search, statusFilter, dateFilter],
    queryFn: () => visitsApi.list({ search, status: statusFilter, date: dateFilter }).then((r) => r.data.visits as Visit[]),
  })

  return (
    <div className="p-8">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-xl font-bold text-gray-900">Visits</h1>
      </div>

      {/* Filters */}
      <div className="flex gap-3 mb-5 flex-wrap">
        <div className="relative flex-1 min-w-48">
          <Search size={14} className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
          <input
            value={search}
            onChange={(e) => setSearch(e.target.value)}
            placeholder="Search officer or institution…"
            className="w-full pl-9 pr-3 py-2 border border-gray-200 rounded-lg text-sm focus:outline-none focus:ring-2 focus:ring-[#1B4F9B]"
          />
        </div>
        <input
          type="date"
          value={dateFilter}
          onChange={(e) => setDateFilter(e.target.value)}
          className="border border-gray-200 rounded-lg px-3 py-2 text-sm"
        />
        <select
          value={statusFilter}
          onChange={(e) => setStatusFilter(e.target.value)}
          className="border border-gray-200 rounded-lg px-3 py-2 text-sm"
        >
          <option value="">All Statuses</option>
          <option value="pending">Pending</option>
          <option value="ongoing">Ongoing</option>
          <option value="completed">Completed</option>
          <option value="missed">Missed</option>
        </select>
      </div>

      {isLoading ? (
        <p className="text-gray-400 text-sm">Loading visits…</p>
      ) : (
        <div className="bg-white rounded-xl border border-gray-100 overflow-hidden">
          <table className="w-full text-sm">
            <thead className="bg-gray-50 border-b border-gray-100">
              <tr>
                {['#', 'Officer', 'Institution', 'Type', 'Date', 'Status', 'Attempt'].map((h) => (
                  <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-gray-500 uppercase tracking-wide">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-50">
              {(data || []).map((visit) => (
                <tr
                  key={visit.id}
                  onClick={() => setSelectedVisit(visit)}
                  className="hover:bg-gray-50 cursor-pointer"
                >
                  <td className="px-4 py-3 text-gray-400 text-xs">{visit.route_order}</td>
                  <td className="px-4 py-3 font-medium text-gray-900">{visit.officer_name}</td>
                  <td className="px-4 py-3 text-gray-700">
                    <div>{visit.institution_name}</div>
                    <div className="text-xs text-gray-400">{visit.institution_address}</div>
                  </td>
                  <td className="px-4 py-3 text-gray-500 capitalize">{visit.institution_type}</td>
                  <td className="px-4 py-3 text-gray-500">{visit.scheduled_date}</td>
                  <td className="px-4 py-3">
                    <span className={`px-2 py-0.5 rounded-full text-xs font-medium ${statusColors[visit.status]}`}>
                      {visit.status}
                    </span>
                  </td>
                  <td className="px-4 py-3 text-gray-400 text-xs">
                    {visit.attempt_count > 1 ? `${visit.attempt_count}x` : '-'}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {selectedVisit && (
        <VisitDetailModal visit={selectedVisit} onClose={() => setSelectedVisit(null)} />
      )}
    </div>
  )
}
