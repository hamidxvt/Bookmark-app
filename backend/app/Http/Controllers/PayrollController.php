<?php

namespace App\Http\Controllers;

use App\Models\PayrollLedger;
use Illuminate\Http\Request;

class PayrollController extends Controller
{
    public function myEarnings(Request $request)
    {
        $officer = $request->user();
        $month = now()->startOfMonth();

        $ledger = PayrollLedger::where('officer_id', $officer->id)
            ->where('month', $month)->first();

        $workingDays = \App\Models\Visit::where('officer_id', $officer->id)
            ->whereMonth('scheduled_date', now()->month)
            ->where('status', 'completed')
            ->distinct('scheduled_date')->count('scheduled_date');

        return response()->json([
            'basic_salary' => $ledger?->basic_salary ?? $officer->basic_salary ?? 0,
            'security_deposit_held' => $ledger?->security_deposit_held ?? $officer->security_deposit ?? 0,
            'performance_earned' => $ledger?->performance_earned ?? 0,
            'deductions' => $ledger?->deductions ?? 0,
            'net_payout' => ($ledger?->basic_salary ?? $officer->basic_salary ?? 0)
                + ($ledger?->performance_earned ?? 0)
                - ($ledger?->deductions ?? 0),
            'working_days_completed' => $workingDays,
            'deduction_reasons' => $ledger?->deduction_reasons ?? [],
        ]);
    }
}
