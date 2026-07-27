import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { institutionsApi } from '../../api/client'
import type { Institution, Visit } from '../../types'
import { Search, School, BookOpen, X } from 'lucide-react'

function HistoryModal({ institution, onClose }: { institution: Institution; onClose: () => void }) {
  const { data: history } = useQuery({
    queryKey: ['institution-history', institution.id],
    queryFn: () => institutionsApi.history(institution.id).then((r) => r.data.visits as Visit[]),
  })

  return (
    <div className="fixed inset-0 bg-black/40 z-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl w-full max-w-2xl max-h-[85vh] overflow-y-auto">
        <div className="flex items-center justify-between px-6 py-4 border-b sticky top-0 bg-white">
          <div>
            <h2 className="font-bold text-gray-900">{institution.name}</h2>
            <p className="text-xs text-gray-400">{institution.address} · {institution.area}, {institution.city}</p>
          </div>
          <button onClick={onClose} className="p-2 hover:bg-gray-100 rounded-lg"><X size={16} /></button>
        </div>
        <div className="p-6">
          <p className="text-sm font-semibold text-gray-500 uppercase mb-3">Complete Visit History — All Officers</p>
          {(history || []).length === 0 && <p className="text-gray-400 text-sm">No visits recorded yet.</p>}
          <div className="space-y-2">
            {(history || []).map((v) => (
              <div key={v.id} className="flex items-center gap-3 text-sm border-b border-gray-50 pb-2">
                <span className="text-gray-400 w-24 flex-shrink-0">{v.scheduled_date}</span>
                <span className="font-medium text-gray-700">{v.officer_name}</span>
                <span className={`ml-auto px-2 py-0.5 rounded-full text-xs ${v.status === 'completed' ? 'bg-green-100 text-green-700' : v.status === 'missed' ? 'bg-red-100 text-red-700' : 'bg-gray-100 text-gray-600'}`}>{v.status}</span>
                {v.visit_type && <span className="text-gray-400">{v.visit_type}</span>}
              </div>
            ))}
          </div>
        </div>
      </div>
    </div>
  )
}

export default function InstitutionsPage() {
  const [search, setSearch] = useState('')
  const [typeFilter, setTypeFilter] = useState('')
  const [selected, setSelected] = useState<Institution | null>(null)

  const { data, isLoading } = useQuery({
    queryKey: ['institutions', search, typeFilter],
    queryFn: () => institutionsApi.list({ search, type: typeFilter }).then((r) => r.data.institutions as Institution[]),
  })

  return (
    <div className="p-8">
      <h1 className="text-xl font-bold text-gray-900 mb-5">Institutions</h1>

      <div className="flex gap-3 mb-5">
        <div className="relative flex-1">
          <Search size={14} className="absolute left-3 top-1/2 -translate-y-1/2 text-gray-400" />
          <input value={search} onChange={(e) => setSearch(e.target.value)} placeholder="Search by name or area…"
            className="w-full pl-9 pr-3 py-2 border border-gray-200 rounded-lg text-sm" />
        </div>
        <select value={typeFilter} onChange={(e) => setTypeFilter(e.target.value)} className="border border-gray-200 rounded-lg px-3 py-2 text-sm">
          <option value="">All Types</option>
          <option value="school">Schools</option>
          <option value="bookshop">Bookshops</option>
        </select>
      </div>

      {isLoading ? <p className="text-gray-400 text-sm">Loading…</p> : (
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-3 gap-3">
          {(data || []).map((inst) => (
            <div key={inst.id} onClick={() => setSelected(inst)}
              className="bg-white rounded-xl border border-gray-100 p-4 hover:border-[#1B4F9B]/30 cursor-pointer transition-colors">
              <div className="flex items-start gap-3">
                <div className={`p-2 rounded-lg flex-shrink-0 ${inst.type === 'school' ? 'bg-blue-50' : 'bg-amber-50'}`}>
                  {inst.type === 'school' ? <School size={16} className="text-blue-600" /> : <BookOpen size={16} className="text-amber-600" />}
                </div>
                <div className="flex-1 min-w-0">
                  <p className="font-medium text-gray-900 text-sm truncate">{inst.name}</p>
                  <p className="text-xs text-gray-400 truncate">{inst.area}, {inst.city}</p>
                </div>
                <span className={`text-xs px-1.5 py-0.5 rounded font-medium ${inst.priority === 'high' ? 'bg-red-100 text-red-700' : inst.priority === 'medium' ? 'bg-amber-100 text-amber-700' : 'bg-gray-100 text-gray-500'}`}>
                  {inst.priority}
                </span>
              </div>
              <div className="mt-3 flex items-center justify-between text-xs text-gray-400">
                <span>{inst.total_visits} visits total</span>
                {inst.last_visit_date && <span>Last: {inst.last_visit_date}</span>}
              </div>
            </div>
          ))}
        </div>
      )}

      {selected && <HistoryModal institution={selected} onClose={() => setSelected(null)} />}
    </div>
  )
}
