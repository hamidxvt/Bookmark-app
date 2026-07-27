import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { leavesApi } from '../../api/client'
import type { LeaveRequest } from '../../types'
import { CheckCircle, XCircle } from 'lucide-react'

const typeColors = { sick: 'bg-blue-100 text-blue-700', casual: 'bg-green-100 text-green-700' }
const statusColors: Record<string, string> = {
  pending: 'bg-amber-100 text-amber-700',
  approved: 'bg-green-100 text-green-700',
  rejected: 'bg-red-100 text-red-700',
  auto: 'bg-gray-100 text-gray-600',
}

export default function LeavesPage() {
  const qc = useQueryClient()

  const { data: requests } = useQuery({
    queryKey: ['leaves'],
    queryFn: () => leavesApi.list().then((r) => r.data.requests as LeaveRequest[]),
  })

  const approve = useMutation({
    mutationFn: (id: number) => leavesApi.approve(id),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['leaves'] }),
  })
  const reject = useMutation({
    mutationFn: (id: number) => leavesApi.reject(id),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['leaves'] }),
  })

  return (
    <div className="p-8">
      <h1 className="text-xl font-bold text-gray-900 mb-6">Leave Requests</h1>
      <div className="bg-white rounded-xl border border-gray-100 overflow-hidden">
        <table className="w-full text-sm">
          <thead className="bg-gray-50 border-b">
            <tr>
              {['Officer', 'Date', 'Type', 'Reason', 'Status', 'Actions'].map((h) => (
                <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-gray-500 uppercase">{h}</th>
              ))}
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-50">
            {(requests || []).map((req) => (
              <tr key={req.id} className="hover:bg-gray-50">
                <td className="px-4 py-3 font-medium text-gray-900">{req.officer_name}</td>
                <td className="px-4 py-3 text-gray-600">{req.date}</td>
                <td className="px-4 py-3">
                  <span className={`px-2 py-0.5 rounded-full text-xs font-medium ${typeColors[req.type]}`}>{req.type}</span>
                </td>
                <td className="px-4 py-3 text-gray-500 max-w-xs truncate">{req.reason}</td>
                <td className="px-4 py-3">
                  <span className={`px-2 py-0.5 rounded-full text-xs font-medium ${statusColors[req.status]}`}>{req.status}</span>
                </td>
                <td className="px-4 py-3">
                  {req.status === 'pending' && (
                    <div className="flex gap-2">
                      <button onClick={() => approve.mutate(req.id)} className="flex items-center gap-1 px-2 py-1 bg-green-600 text-white text-xs rounded hover:bg-green-700">
                        <CheckCircle size={12} /> Approve
                      </button>
                      <button onClick={() => reject.mutate(req.id)} className="flex items-center gap-1 px-2 py-1 bg-red-100 text-red-700 text-xs rounded hover:bg-red-200">
                        <XCircle size={12} /> Reject
                      </button>
                    </div>
                  )}
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  )
}
