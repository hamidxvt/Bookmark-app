'use client';

import { useState, useEffect } from 'react';
import { MapPin, Clock, User, Phone, Plus, Trash2, Eye } from 'lucide-react';
import Link from 'next/link';

interface AdHocVisit {
  id: number;
  booker: { id: number; name: string; email: string };
  customer: { id: number; name: string; city?: string; phone?: string };
  customerName: string;
  notes?: string;
  createdAt: string;
}

export default function AdHocVisitsPage() {
  const [visits, setVisits] = useState<AdHocVisit[]>([]);
  const [loading, setLoading] = useState(true);
  const [filter, setFilter] = useState('all'); // all, today, pending

  useEffect(() => {
    fetchAdHocVisits();
  }, [filter]);

  const fetchAdHocVisits = async () => {
    try {
      setLoading(true);
      const params = new URLSearchParams();
      if (filter !== 'all') params.append('filter', filter);
      const res = await fetch(`/api/v1/adhoc-visits?${params}`);
      const data = await res.json();
      setVisits(data.data || []);
    } catch (err) {
      console.error('Failed to fetch ad-hoc visits:', err);
    } finally {
      setLoading(false);
    }
  };

  const deleteVisit = async (id: number) => {
    if (!confirm('Delete this ad-hoc visit?')) return;
    try {
      await fetch(`/api/v1/adhoc-visits/${id}`, { method: 'DELETE' });
      setVisits(visits.filter(v => v.id !== id));
    } catch (err) {
      console.error('Failed to delete visit:', err);
    }
  };

  return (
    <div className="p-6 space-y-6">
      <div className="flex justify-between items-center">
        <h1 className="text-3xl font-bold text-gray-900">Ad-hoc Visits</h1>
        <Link
          href="/adhoc-visits/new"
          className="flex items-center gap-2 px-4 py-2 bg-red-600 text-white rounded-lg hover:bg-red-700 transition"
        >
          <Plus size={18} />
          Create Visit
        </Link>
      </div>

      <div className="flex gap-2">
        {['all', 'today', 'pending'].map(opt => (
          <button
            key={opt}
            onClick={() => setFilter(opt)}
            className={`px-4 py-2 rounded-lg font-medium transition ${
              filter === opt
                ? 'bg-red-600 text-white'
                : 'bg-gray-100 text-gray-700 hover:bg-gray-200'
            }`}
          >
            {opt.charAt(0).toUpperCase() + opt.slice(1)}
          </button>
        ))}
      </div>

      {loading ? (
        <div className="text-center py-12 text-gray-500">Loading...</div>
      ) : visits.length === 0 ? (
        <div className="text-center py-12 text-gray-500">No ad-hoc visits found</div>
      ) : (
        <div className="grid gap-4">
          {visits.map(visit => (
            <div
              key={visit.id}
              className="bg-white rounded-lg border border-gray-200 p-4 hover:shadow-md transition"
            >
              <div className="flex justify-between items-start mb-3">
                <div className="flex-1">
                  <h3 className="text-lg font-bold text-gray-900">{visit.customerName}</h3>
                  <p className="text-sm text-gray-500">{visit.customer?.city || 'N/A'}</p>
                </div>
                <div className="flex gap-2">
                  <button
                    onClick={() => deleteVisit(visit.id)}
                    className="p-2 text-red-600 hover:bg-red-50 rounded transition"
                  >
                    <Trash2 size={18} />
                  </button>
                </div>
              </div>

              <div className="grid grid-cols-2 gap-3 text-sm">
                <div className="flex gap-2 items-center text-gray-600">
                  <User size={16} className="text-red-600" />
                  <span>{visit.booker.name}</span>
                </div>
                <div className="flex gap-2 items-center text-gray-600">
                  <Phone size={16} className="text-red-600" />
                  <span>{visit.customer?.phone || 'N/A'}</span>
                </div>
                <div className="flex gap-2 items-center text-gray-600">
                  <Clock size={16} className="text-red-600" />
                  <span>{new Date(visit.createdAt).toLocaleDateString()}</span>
                </div>
                {visit.notes && (
                  <div className="col-span-2 text-gray-600">
                    <span className="text-xs font-semibold">Notes:</span> {visit.notes}
                  </div>
                )}
              </div>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
