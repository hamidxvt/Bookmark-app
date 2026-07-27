<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\PayrollLedger;
use App\Models\User;
use Illuminate\Http\Request;

class AdminPayrollController extends Controller
{
    public function ledger(Request $request)
    {
        $month = $request->month ? \Carbon\Carbon::parse($request->month)->startOfMonth() : now()->startOfMonth();

        $query = PayrollLedger::with('officer')->where('month', $month);
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }

        $ledger = $query->get()->map(fn($r) => [
            'id' => $r->id,
            'officer_id' => $r->officer_id,
            'officer_name' => $r->officer?->name,
            'month' => $r->month->format('Y-m'),
            'basic_salary' => $r->basic_salary,
            'security_deposit_held' => $r->security_deposit_held,
            'performance_earned' => $r->performance_earned,
            'deductions' => $r->deductions,
            'net_payout' => $r->net_payout,
            'deduction_reasons' => $r->deduction_reasons ?? [],
        ]);

        return response()->json(['ledger' => $ledger]);
    }

    public function officerSummary(int $id, Request $request)
    {
        $month = $request->month ? \Carbon\Carbon::parse($request->month)->startOfMonth() : now()->startOfMonth();
        $record = PayrollLedger::where('officer_id', $id)->where('month', $month)->first();
        $officer = User::findOrFail($id);

        return response()->json([
            'officer_name' => $officer->name,
            'month' => $month->format('Y-m'),
            'basic_salary' => $record?->basic_salary ?? $officer->basic_salary,
            'security_deposit_held' => $record?->security_deposit_held ?? $officer->security_deposit,
            'performance_earned' => $record?->performance_earned ?? 0,
            'deductions' => $record?->deductions ?? 0,
            'net_payout' => $record?->net_payout ?? $officer->basic_salary,
            'deduction_reasons' => $record?->deduction_reasons ?? [],
        ]);
    }
}
