import { useState } from 'react'
import { useQuery, useMutation, useQueryClient } from '@tanstack/react-query'
import { missedVisitsApi } from '../../api/client'
import type { Visit } from '../../types'
import { CheckCircle, XCircle } from 'lucide-react'
import { useAuthStore } from '../../hooks/useAuth'

export default function MissedVisitsPage() {
  const qc = useQueryClient()
  const { user } = useAuthStore()
  const isAdmin = user?.role === 'admin'
  const [commentMap, setCommentMap] = useState<Record<number, string>>({})

  const { data: visits, isLoading } = useQuery({
    queryKey: ['missed-visits-pending'],
    queryFn: () => missedVisitsApi.pending().then((r) => r.data.visits as Visit[]),
  })

  const approve = useMutation({
    mutationFn: ({ id, comment }: { id: number; comment: string }) => missedVisitsApi.approve(id, comment),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['missed-visits-pending'] }),
  })

  const reject = useMutation({
    mutationFn: ({ id, comment }: { id: number; comment: string }) => missedVisitsApi.reject(id, comment),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['missed-visits-pending'] }),
  })

  const override = useMutation({
    mutationFn: ({ id, decision, comment }: { id: number; decision: string; comment: string }) =>
      missedVisitsApi.override(id, decision, comment),
    onSuccess: () => qc.invalidateQueries({ queryKey: ['missed-visits-pending'] }),
  })

  if (isLoading) return <div className="p-8 text-gray-400">Loading…</div>

  return (
    <div className="p-8">
      <h1 className="text-xl font-bold text-gray-900 mb-2">Missed Visit Reviews</h1>
      <p className="text-sm text-gray-500 mb-6">
        {isAdmin ? 'You can approve, reject, or override any decision.' : 'Review and approve or reject missed visit justifications.'}
      </p>

      {(visits || []).length === 0 && (
        <div className="bg-white rounded-xl border border-gray-100 p-12 text-center">
          <CheckCircle size={32} className="text-green-400 mx-auto mb-3" />
          <p className="text-gray-500">No pending missed visit reviews.</p>
        </div>
      )}

      <div className="space-y-4">
        {(visits || []).map((visit) => (
          <div key={visit.id} className="bg-white rounded-xl border border-gray-100 p-5">
            <div className="flex items-start gap-4">
              {visit.missed_photo && (
                <img src={visit.missed_photo} alt="Evidence" className="w-24 h-24 object-cover rounded-lg flex-shrink-0" />
              )}
              <div className="flex-1 min-w-0">
                <div className="flex items-center gap-2 flex-wrap mb-1">
                  <span className="font-semibold text-gray-900">{visit.officer_name}</span>
                  <span className="text-gray-400 text-sm">—</span>
                  <span className="text-sm text-gray-700">{visit.institution_name}</span>
                  <span className="text-xs text-gray-400">{visit.scheduled_date}</span>
                  {visit.attempt_count > 1 && (
                    <span className="px-2 py-0.5 bg-amber-100 text-amber-700 text-xs rounded-full">Attempt {visit.attempt_count}</span>
                  )}
                </div>

                <p className="text-sm text-gray-600 mb-3 bg-gray-50 rounded-lg px-3 py-2">
                  <strong>Reason:</strong> {visit.missed_reason}
                </p>

                {visit.missed_status && visit.missed_status !== 'pending_review' ? (
                  <div className={`text-sm px-3 py-1.5 rounded-lg inline-flex items-center gap-1.5 ${
                    visit.missed_status === 'approved' ? 'bg-green-50 text-green-700' : 'bg-red-50 text-red-700'
                  }`}>
                    {visit.missed_status === 'approved' ? <CheckCircle size={14} /> : <XCircle size={14} />}
                    {visit.missed_status} by City Head
                    {isAdmin && ' — override below'}
                  </div>
                ) : (
                  <div className="flex items-center gap-2 flex-wrap">
                    <input
                      value={commentMap[visit.id] || ''}
                      onChange={(e) => setCommentMap({ ...commentMap, [visit.id]: e.target.value })}
                      placeholder="Comment (optional)"
                      className="flex-1 border border-gray-200 rounded-lg px-3 py-1.5 text-sm min-w-36"
                    />
                    <button
                      onClick={() => approve.mutate({ id: visit.id, comment: commentMap[visit.id] || '' })}
                      className="px-3 py-1.5 bg-green-600 text-white text-sm rounded-lg hover:bg-green-700 flex items-center gap-1"
                    >
                      <CheckCircle size={14} /> Approve
                    </button>
                    <button
                      onClick={() => reject.mutate({ id: visit.id, comment: commentMap[visit.id] || '' })}
                      className="px-3 py-1.5 bg-red-600 text-white text-sm rounded-lg hover:bg-red-700 flex items-center gap-1"
                    >
                      <XCircle size={14} /> Reject
                    </button>
                  </div>
                )}

                {isAdmin && visit.missed_status && visit.missed_status !== 'pending_review' && (
                  <div className="flex gap-2 mt-2">
                    <button
                      onClick={() => override.mutate({ id: visit.id, decision: 'approved', comment: 'Admin override' })}
                      className="px-3 py-1 bg-green-100 text-green-700 text-xs rounded-lg hover:bg-green-200"
                    >Override → Approve</button>
                    <button
                      onClick={() => override.mutate({ id: visit.id, decision: 'rejected', comment: 'Admin override' })}
                      className="px-3 py-1 bg-red-100 text-red-700 text-xs rounded-lg hover:bg-red-200"
                    >Override → Reject</button>
                  </div>
                )}
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  )
}
