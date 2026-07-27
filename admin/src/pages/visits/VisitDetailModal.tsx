import { X, MapPin, Clock, User, Phone } from 'lucide-react'
import type { Visit } from '../../types'

export default function VisitDetailModal({ visit, onClose }: { visit: Visit; onClose: () => void }) {
  return (
    <div className="fixed inset-0 bg-black/40 z-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl w-full max-w-2xl max-h-[90vh] overflow-y-auto">
        <div className="flex items-center justify-between px-6 py-4 border-b">
          <div>
            <h2 className="font-bold text-gray-900">{visit.institution_name}</h2>
            <p className="text-sm text-gray-500">{visit.institution_address}</p>
          </div>
          <button onClick={onClose} className="p-2 hover:bg-gray-100 rounded-lg"><X size={18} /></button>
        </div>

        <div className="p-6 space-y-5">
          {/* Status / meta */}
          <div className="flex gap-4 flex-wrap">
            <span className={`px-3 py-1 rounded-full text-sm font-medium ${
              visit.status === 'completed' ? 'bg-green-100 text-green-700' :
              visit.status === 'missed' ? 'bg-red-100 text-red-700' :
              'bg-gray-100 text-gray-600'
            }`}>{visit.status}</span>
            <span className="text-sm text-gray-500">Officer: <strong>{visit.officer_name}</strong></span>
            <span className="text-sm text-gray-500">Date: <strong>{visit.scheduled_date}</strong></span>
            {visit.attempt_count > 1 && (
              <span className="px-2 py-0.5 bg-amber-100 text-amber-700 text-xs rounded-full">Attempt {visit.attempt_count}</span>
            )}
          </div>

          {/* Contact info */}
          {visit.contact_name && (
            <div className="grid grid-cols-2 gap-4 bg-gray-50 rounded-xl p-4">
              <div className="flex items-center gap-2 text-sm">
                <User size={14} className="text-gray-400" />
                <span>{visit.contact_name}</span>
              </div>
              <div className="flex items-center gap-2 text-sm">
                <span className="text-gray-400 text-xs uppercase">Designation</span>
                <span>{visit.designation}</span>
              </div>
              <div className="flex items-center gap-2 text-sm">
                <Phone size={14} className="text-gray-400" />
                <span>{visit.contact_phone}</span>
              </div>
              <div className="flex items-center gap-2 text-sm">
                <span className="text-gray-400 text-xs uppercase">Type</span>
                <span>{visit.visit_type}</span>
              </div>
            </div>
          )}

          {/* GPS / timing */}
          {(visit.travel_time_mins || visit.onsite_time_mins) && (
            <div className="flex gap-6 text-sm text-gray-600">
              {visit.travel_time_mins && (
                <div className="flex items-center gap-1.5"><Clock size={14} className="text-gray-400" /> Travel: {visit.travel_time_mins} min</div>
              )}
              {visit.onsite_time_mins && (
                <div className="flex items-center gap-1.5"><MapPin size={14} className="text-gray-400" /> On-site: {visit.onsite_time_mins} min</div>
              )}
            </div>
          )}

          {/* Notes */}
          {visit.notes && (
            <div>
              <p className="text-xs font-semibold text-gray-400 uppercase mb-1">Discussion Notes</p>
              <p className="text-sm text-gray-700 bg-gray-50 rounded-lg p-3">{visit.notes}</p>
            </div>
          )}

          {/* Follow up */}
          {visit.followup_date && (
            <div className="text-sm text-[#1B4F9B] bg-blue-50 rounded-lg px-3 py-2">
              Follow-up scheduled: <strong>{visit.followup_date}</strong>
            </div>
          )}

          {/* Missed reason */}
          {visit.status === 'missed' && (
            <div>
              <p className="text-xs font-semibold text-red-400 uppercase mb-1">Missed Reason</p>
              <p className="text-sm text-gray-700 bg-red-50 rounded-lg p-3">{visit.missed_reason}</p>
              {visit.missed_photo && (
                <img src={visit.missed_photo} alt="Evidence" className="mt-2 rounded-lg w-full max-h-48 object-cover" />
              )}
            </div>
          )}

          {/* Coordinator notes */}
          {visit.coordinator_notes && (
            <div className="bg-amber-50 rounded-lg px-3 py-2 text-sm text-amber-800">
              <strong>Coordinator Note:</strong> {visit.coordinator_notes}
            </div>
          )}
        </div>
      </div>
    </div>
  )
}
