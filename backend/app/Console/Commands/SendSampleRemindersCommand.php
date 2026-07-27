<?php

namespace App\Console\Commands;

use App\Models\PayrollLedger;
use App\Models\SampleRequest;
use Illuminate\Console\Command;

/**
 * Checks approved but un-recovered sample requests.
 * Sends reminders at 10 and 20 days post-approval.
 * Auto-deducts salary at 30 days.
 */
class SendSampleRemindersCommand extends Command
{
    protected $signature = 'samples:reminders';
    protected $description = 'Send 10/20-day reminders and trigger payroll deduction for unrecovered samples';

    public function handle(): void
    {
        $approved = SampleRequest::with('officer')
            ->where('status', 'approved')
            ->whereNull('deducted_from_payroll')
            ->get();

        foreach ($approved as $request) {
            $daysSince = $request->approved_at->diffInDays(now());

            // 10-day reminder
            if ($daysSince >= 10 && is_null($request->reminder_10_sent_at)) {
                $this->sendReminder($request, 10);
                $request->update(['reminder_10_sent_at' => now()]);
            }

            // 20-day reminder
            if ($daysSince >= 20 && is_null($request->reminder_20_sent_at)) {
                $this->sendReminder($request, 20);
                $request->update(['reminder_20_sent_at' => now()]);
            }

            // 30-day auto payroll deduction
            if ($daysSince >= 30 && !$request->deducted_from_payroll) {
                $this->deductFromPayroll($request);
                $request->update(['deducted_from_payroll' => true, 'deducted_at' => now()]);
            }
        }

        $this->info('Sample reminders processed');
    }

    private function sendReminder(SampleRequest $request, int $days): void
    {
        // TODO: Send SMS notification to officer
        $this->info("  {$days}-day reminder sent to {$request->officer->name} for PKR {$request->total_pkr}");
    }

    private function deductFromPayroll(SampleRequest $request): void
    {
        $month = now()->startOfMonth();
        $officer = $request->officer;

        $payroll = PayrollLedger::firstOrCreate(
            ['officer_id' => $officer->id, 'month' => $month],
            [
                'basic_salary' => $officer->basic_salary ?? 0,
                'security_deposit_held' => $officer->security_deposit ?? 0,
                'performance_earned' => 0,
                'deductions' => 0,
                'deduction_reasons' => [],
            ]
        );

        $reasons = $payroll->deduction_reasons ?? [];
        $reasons[] = "Unrecovered sample request #{$request->id} (PKR {$request->total_pkr})";

        $payroll->update([
            'deductions' => $payroll->deductions + $request->total_pkr,
            'deduction_reasons' => $reasons,
        ]);

        $this->info("  Auto-deducted PKR {$request->total_pkr} from {$officer->name}'s payroll");
    }
}
