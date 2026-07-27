<?php

namespace App\Console\Commands;

use App\Models\Attendance;
use App\Models\LeaveRequest;
use App\Models\User;
use Carbon\Carbon;
use Illuminate\Console\Command;

/**
 * Runs after business hours (e.g. 11 PM).
 * Auto-deducts 1 day from leave balance for officers who didn't start their day
 * and had no approved leave / cannot_work declaration.
 */
class ProcessAttendanceCommand extends Command
{
    protected $signature = 'attendance:process {--date= : Date to process (Y-m-d), defaults to today}';
    protected $description = 'Auto-deduct leave for officers who missed day start with no valid reason';

    public function handle(): void
    {
        $date = $this->option('date') ? Carbon::parse($this->option('date')) : today();

        $officers = User::where('role', 'officer')->where('is_active', true)->get();

        foreach ($officers as $officer) {
            $attendance = Attendance::where('officer_id', $officer->id)->whereDate('date', $date)->first();

            // Officer was present
            if ($attendance?->isStarted()) continue;

            // Officer declared cannot work
            if ($attendance?->status === 'cannot_work') continue;

            // Officer has approved leave
            $hasLeave = LeaveRequest::where('officer_id', $officer->id)
                ->whereDate('date', $date)
                ->where('status', 'approved')
                ->exists();
            if ($hasLeave) continue;

            // No attendance, no cannot_work, no leave → auto-deduct
            $this->autoDeductLeave($officer, $date);
        }

        $this->info("Attendance processed for {$date->format('Y-m-d')}");
    }

    private function autoDeductLeave(User $officer, Carbon $date): void
    {
        // Create auto leave record
        LeaveRequest::create([
            'officer_id' => $officer->id,
            'date' => $date,
            'type' => 'casual',
            'status' => 'auto',
            'reason' => 'Auto-deducted: no day start recorded',
        ]);

        // Deduct from casual balance (or sick if casual is 0)
        if ($officer->leave_casual_balance > 0) {
            $officer->decrement('leave_casual_balance');
        } elseif ($officer->leave_sick_balance > 0) {
            $officer->decrement('leave_sick_balance');
        }
        // If both are 0, leave balances go to 0 (negative handled separately)

        $this->info("  Auto-deducted leave for {$officer->name} on {$date->format('Y-m-d')}");
    }
}
