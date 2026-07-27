import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { officersApi } from '../../api/client'
import type { User } from '../../types'
import { Plus, RotateCcw } from 'lucide-react'
import OfficerFormModal from './OfficerFormModal'

export default function OfficersPage() {
  const qc = useQueryClient()
  const [showForm, setShowForm] = useState(false)
  const [editOfficer, setEditOfficer] = useState<User | null>(null)

  const { data: officers, isLoading } = useQuery({
    queryKey: ['officers'],
    queryFn: () => officersApi.list().then((r) => r.data.officers as User[]),
  })

  const resetPassword = useMutation({
    mutationFn: (id: number) => officersApi.resetPassword(id),
    onSuccess: () => alert('Password reset. New credentials sent to officer.'),
  })

  return (
    <div className="p-8">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-xl font-bold text-gray-900">Officers</h1>
        <button
          onClick={() => { setEditOfficer(null); setShowForm(true) }}
          className="flex items-center gap-2 px-4 py-2 bg-[#1B4F9B] text-white text-sm rounded-lg hover:bg-[#0D3570]"
        >
          <Plus size={15} /> Add Officer
        </button>
      </div>

      {isLoading ? <p className="text-gray-400 text-sm">Loading…</p> : (
        <div className="bg-white rounded-xl border border-gray-100 overflow-hidden">
          <table className="w-full text-sm">
            <thead className="bg-gray-50 border-b">
              <tr>
                {['Name', 'Phone', 'Role', 'City / Area', 'Sick Leave', 'Casual Leave', 'Sample Limit', 'Actions'].map((h) => (
                  <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-gray-500 uppercase">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-50">
              {(officers || []).map((o) => (
                <tr key={o.id} className="hover:bg-gray-50">
                  <td className="px-4 py-3 font-medium text-gray-900">{o.name}</td>
                  <td className="px-4 py-3 text-gray-600">{o.phone}</td>
                  <td className="px-4 py-3 capitalize text-gray-600">{o.role.replace('_', ' ')}</td>
                  <td className="px-4 py-3 text-gray-500">{o.city} / {o.area}</td>
                  <td className="px-4 py-3 text-gray-500">{o.leave_sick_balance ?? 10} / 10</td>
                  <td className="px-4 py-3 text-gray-500">{o.leave_casual_balance ?? 18} / 18</td>
                  <td className="px-4 py-3 text-gray-500">PKR {o.annual_sample_limit?.toLocaleString() ?? '—'}</td>
                  <td className="px-4 py-3">
                    <div className="flex gap-2">
                      <button
                        onClick={() => { setEditOfficer(o); setShowForm(true) }}
                        className="text-xs px-2 py-1 border border-gray-200 rounded hover:bg-gray-50"
                      >Edit</button>
                      <button
                        onClick={() => { if (confirm('Reset this officer\'s password?')) resetPassword.mutate(o.id) }}
                        className="text-xs px-2 py-1 text-amber-700 border border-amber-200 rounded hover:bg-amber-50 flex items-center gap-1"
                      >
                        <RotateCcw size={11} /> Reset PW
                      </button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}

      {showForm && (
        <OfficerFormModal
          officer={editOfficer}
          onClose={() => setShowForm(false)}
          onSaved={() => { setShowForm(false); qc.invalidateQueries({ queryKey: ['officers'] }) }}
        />
      )}
    </div>
  )
}
