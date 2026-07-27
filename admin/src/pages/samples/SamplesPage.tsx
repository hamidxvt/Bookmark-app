import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { samplesApi } from '../../api/client'
import type { SampleRequest } from '../../types'
import { CheckCircle, XCircle, Package } from 'lucide-react'

const statusColors: Record<string, string> = {
  pending: 'bg-amber-100 text-amber-700',
  approved: 'bg-green-100 text-green-700',
  rejected: 'bg-red-100 text-red-700',
  recovered: 'bg-blue-100 text-blue-700',
}

export default function SamplesPage() {
  const qc = useQueryClient()
  const [tab, setTab] = useState<'requests' | 'ledger'>('requests')

  const { data: requests } = useQuery({
    queryKey: ['sample-requests'],
    queryFn: () => samplesApi.requests({ status: 'pending' }).then((r) => r.data.requests as SampleRequest[]),
    enabled: tab === 'requests',
  })

  const { data: ledger } = useQuery({
    queryKey: ['sample-ledger'],
    queryFn: () => samplesApi.ledger().then((r) => r.data.requests as SampleRequest[]),
    enabled: tab === 'ledger',
  })

  const approve = useMutation({
    mutationFn: (id: number) => samplesApi.approve(id),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['sample-requests'] }),
  })
  const reject = useMutation({
    mutationFn: (id: number) => samplesApi.reject(id),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['sample-requests'] }),
  })

  const fmt = (n: number) => `PKR ${n.toLocaleString()}`

  return (
    <div className="p-8">
      <h1 className="text-xl font-bold text-gray-900 mb-5">Sample Management</h1>

      <div className="flex gap-2 mb-6">
        {(['requests', 'ledger'] as const).map((t) => (
          <button
            key={t}
            onClick={() => setTab(t)}
            className={`px-4 py-2 rounded-lg text-sm font-medium transition-colors ${tab === t ? 'bg-[#1B4F9B] text-white' : 'bg-white border border-gray-200 text-gray-600 hover:bg-gray-50'}`}
          >
            {t === 'requests' ? 'Pending Approvals' : 'Full Ledger'}
          </button>
        ))}
      </div>

      {tab === 'requests' && (
        <div className="space-y-3">
          {(requests || []).length === 0 && (
            <div className="bg-white rounded-xl border p-12 text-center">
              <Package size={32} className="text-gray-300 mx-auto mb-3" />
              <p className="text-gray-400">No pending sample requests.</p>
            </div>
          )}
          {(requests || []).map((req) => (
            <div key={req.id} className="bg-white rounded-xl border border-gray-100 p-5">
              <div className="flex items-start justify-between gap-4">
                <div>
                  <div className="flex items-center gap-2 mb-1">
                    <span className="font-semibold text-gray-900">{req.officer_name}</span>
                    <span className={`px-2 py-0.5 rounded-full text-xs font-medium ${statusColors[req.status]}`}>{req.status}</span>
                  </div>
                  <p className="text-sm text-gray-500 mb-2">Requested: {new Date(req.created_at).toLocaleDateString()}</p>
                  <div className="space-y-0.5">
                    {req.items?.map((item, i) => (
                      <p key={i} className="text-sm text-gray-600">{item.product_name} × {item.quantity} — {fmt(item.value)}</p>
                    ))}
                  </div>
                  <p className="mt-2 font-semibold text-gray-900">Total: {fmt(req.total_pkr)}</p>
                </div>
                <div className="flex gap-2 flex-shrink-0">
                  <button onClick={() => approve.mutate(req.id)} className="flex items-center gap-1 px-3 py-1.5 bg-green-600 text-white text-sm rounded-lg hover:bg-green-700">
                    <CheckCircle size={14} /> Approve
                  </button>
                  <button onClick={() => reject.mutate(req.id)} className="flex items-center gap-1 px-3 py-1.5 bg-red-100 text-red-700 text-sm rounded-lg hover:bg-red-200">
                    <XCircle size={14} /> Reject
                  </button>
                </div>
              </div>
            </div>
          ))}
        </div>
      )}

      {tab === 'ledger' && (
        <div className="bg-white rounded-xl border border-gray-100 overflow-hidden">
          <table className="w-full text-sm">
            <thead className="bg-gray-50 border-b">
              <tr>
                {['Officer', 'Total PKR', 'Status', 'Requested', '10-Day Reminder', '20-Day Reminder'].map((h) => (
                  <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-gray-500 uppercase">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-50">
              {(ledger || []).map((req) => (
                <tr key={req.id} className="hover:bg-gray-50">
                  <td className="px-4 py-3 font-medium">{req.officer_name}</td>
                  <td className="px-4 py-3">{fmt(req.total_pkr)}</td>
                  <td className="px-4 py-3"><span className={`px-2 py-0.5 rounded-full text-xs font-medium ${statusColors[req.status]}`}>{req.status}</span></td>
                  <td className="px-4 py-3 text-gray-500">{new Date(req.created_at).toLocaleDateString()}</td>
                  <td className="px-4 py-3 text-gray-500">{req.reminder_10_sent_at ? new Date(req.reminder_10_sent_at).toLocaleDateString() : '—'}</td>
                  <td className="px-4 py-3 text-gray-500">{req.reminder_20_sent_at ? new Date(req.reminder_20_sent_at).toLocaleDateString() : '—'}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  )
}
