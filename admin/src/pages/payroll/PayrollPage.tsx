import { useState } from 'react'
import { useQuery } from '@tanstack/react-query'
import { payrollApi } from '../../api/client'
import type { PayrollRecord } from '../../types'

export default function PayrollPage() {
  const [month, setMonth] = useState(() => new Date().toISOString().slice(0, 7))

  const { data, isLoading } = useQuery({
    queryKey: ['payroll-ledger', month],
    queryFn: () => payrollApi.ledger({ month }).then((r) => r.data.ledger as PayrollRecord[]),
  })

  const fmt = (n: number) => `PKR ${n.toLocaleString()}`

  return (
    <div className="p-8">
      <div className="flex items-center justify-between mb-6">
        <h1 className="text-xl font-bold text-gray-900">Payroll Ledger</h1>
        <input
          type="month"
          value={month}
          onChange={(e) => setMonth(e.target.value)}
          className="border border-gray-200 rounded-lg px-3 py-2 text-sm"
        />
      </div>

      <div className="bg-amber-50 border border-amber-100 rounded-xl px-4 py-3 text-sm text-amber-800 mb-5">
        <strong>Salary Structure:</strong> Basic (unconditional) + Security Deposit (held monthly) + Performance Component (PKR 3,000/day × working days). Performance deducted for rejected missed visits.
      </div>

      {isLoading ? (
        <p className="text-gray-400 text-sm">Loading payroll data…</p>
      ) : (
        <div className="bg-white rounded-xl border border-gray-100 overflow-hidden">
          <table className="w-full text-sm">
            <thead className="bg-gray-50 border-b">
              <tr>
                {['Officer', 'Basic', 'Security Deposit', 'Performance Earned', 'Deductions', 'Net Payout', 'Deduction Reasons'].map((h) => (
                  <th key={h} className="px-4 py-3 text-left text-xs font-semibold text-gray-500 uppercase">{h}</th>
                ))}
              </tr>
            </thead>
            <tbody className="divide-y divide-gray-50">
              {(data || []).map((record) => (
                <tr key={record.id} className="hover:bg-gray-50">
                  <td className="px-4 py-3 font-medium text-gray-900">{record.officer_name}</td>
                  <td className="px-4 py-3 text-gray-600">{fmt(record.basic_salary)}</td>
                  <td className="px-4 py-3 text-amber-600">{fmt(record.security_deposit_held)}</td>
                  <td className="px-4 py-3 text-green-700">{fmt(record.performance_earned)}</td>
                  <td className="px-4 py-3 text-red-600">{fmt(record.deductions)}</td>
                  <td className="px-4 py-3 font-semibold text-gray-900">{fmt(record.net_payout)}</td>
                  <td className="px-4 py-3 text-xs text-gray-500">
                    {record.deduction_reasons?.join(', ') || '—'}
                  </td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
      )}
    </div>
  )
}
