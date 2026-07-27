import { useState, useEffect } from 'react'
import { useQuery, useMutation } from '@tanstack/react-query'
import { officersApi, masterDataApi } from '../../api/client'
import type { User } from '../../types'
import { X } from 'lucide-react'

export default function OfficerFormModal({ officer, onClose, onSaved }: {
  officer: User | null; onClose: () => void; onSaved: () => void
}) {
  const isEdit = !!officer
  const [form, setForm] = useState({
    name: officer?.name ?? '',
    phone: officer?.phone ?? '',
    email: officer?.email ?? '',
    role: officer?.role ?? 'officer',
    city_id: officer?.city_id ?? '',
    area_id: officer?.area_id ?? '',
    basic_salary: officer?.basic_salary ?? '',
    security_deposit: officer?.security_deposit ?? '',
    performance_daily: officer?.performance_daily ?? 3000,
    annual_sample_limit: officer?.annual_sample_limit ?? '',
  })
  const [error, setError] = useState('')

  const { data: cities } = useQuery({ queryKey: ['cities'], queryFn: () => masterDataApi.cities().then((r) => r.data.cities) })
  const { data: areas } = useQuery({
    queryKey: ['areas', form.city_id],
    queryFn: () => masterDataApi.areas(Number(form.city_id)).then((r) => r.data.areas),
    enabled: !!form.city_id,
  })

  const save = useMutation({
    mutationFn: () => isEdit
      ? officersApi.update(officer!.id, form as Record<string, unknown>)
      : officersApi.create(form as Record<string, unknown>),
    onSuccess: onSaved,
    onError: () => setError('Failed to save. Check all fields.'),
  })

  const set = (field: string) => (e: React.ChangeEvent<HTMLInputElement | HTMLSelectElement>) =>
    setForm((f) => ({ ...f, [field]: e.target.value }))

  return (
    <div className="fixed inset-0 bg-black/40 z-50 flex items-center justify-center p-4">
      <div className="bg-white rounded-2xl w-full max-w-lg max-h-[90vh] overflow-y-auto">
        <div className="flex items-center justify-between px-6 py-4 border-b">
          <h2 className="font-bold text-gray-900">{isEdit ? 'Edit Officer' : 'Add New Officer'}</h2>
          <button onClick={onClose}><X size={18} /></button>
        </div>

        <div className="p-6 space-y-4">
          {[
            { label: 'Full Name', field: 'name', type: 'text' },
            { label: 'Phone Number', field: 'phone', type: 'tel' },
            { label: 'Email', field: 'email', type: 'email' },
          ].map(({ label, field, type }) => (
            <div key={field}>
              <label className="block text-sm font-medium text-gray-700 mb-1">{label}</label>
              <input type={type} value={(form as Record<string, unknown>)[field] as string} onChange={set(field)}
                className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm" />
            </div>
          ))}

          <div>
            <label className="block text-sm font-medium text-gray-700 mb-1">Role</label>
            <select value={form.role} onChange={set('role')} className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm">
              <option value="officer">Sales Officer</option>
              <option value="coordinator">Coordinator</option>
              <option value="city_head">City Head</option>
              <option value="admin">Administrator</option>
            </select>
          </div>

          <div className="grid grid-cols-2 gap-3">
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">City</label>
              <select value={form.city_id} onChange={set('city_id')} className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm">
                <option value="">Select city</option>
                {(cities || []).map((c: { id: number; name: string }) => <option key={c.id} value={c.id}>{c.name}</option>)}
              </select>
            </div>
            <div>
              <label className="block text-sm font-medium text-gray-700 mb-1">Area</label>
              <select value={form.area_id} onChange={set('area_id')} className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm">
                <option value="">Select area</option>
                {(areas || []).map((a: { id: number; name: string }) => <option key={a.id} value={a.id}>{a.name}</option>)}
              </select>
            </div>
          </div>

          <div className="border-t pt-4">
            <p className="text-xs font-semibold text-gray-400 uppercase mb-3">Salary Structure (PKR)</p>
            <div className="grid grid-cols-2 gap-3">
              {[
                { label: 'Basic Salary', field: 'basic_salary' },
                { label: 'Security Deposit (monthly)', field: 'security_deposit' },
                { label: 'Daily Performance Rate', field: 'performance_daily' },
                { label: 'Annual Sample Limit', field: 'annual_sample_limit' },
              ].map(({ label, field }) => (
                <div key={field}>
                  <label className="block text-xs text-gray-600 mb-1">{label}</label>
                  <input type="number" value={(form as Record<string, unknown>)[field] as number} onChange={set(field)}
                    className="w-full border border-gray-200 rounded-lg px-3 py-2 text-sm" />
                </div>
              ))}
            </div>
          </div>

          {error && <p className="text-sm text-red-600 bg-red-50 rounded-lg px-3 py-2">{error}</p>}

          <div className="flex gap-3 pt-2">
            <button onClick={onClose} className="flex-1 py-2.5 border border-gray-200 rounded-lg text-sm hover:bg-gray-50">Cancel</button>
            <button
              onClick={() => save.mutate()}
              disabled={save.isPending}
              className="flex-1 py-2.5 bg-[#1B4F9B] text-white rounded-lg text-sm hover:bg-[#0D3570] disabled:opacity-50"
            >
              {save.isPending ? 'Saving…' : isEdit ? 'Save Changes' : 'Create Officer'}
            </button>
          </div>
        </div>
      </div>
    </div>
  )
}
