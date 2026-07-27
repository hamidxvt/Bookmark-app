<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Visit extends Model
{
    use HasFactory;

    protected $fillable = [
        'officer_id', 'institution_id', 'scheduled_date', 'status', 'source',
        'priority', 'route_order', 'attempt_count', 'coordinator_notes',
        'started_at', 'completed_at',
        'start_lat', 'start_lng', 'checkin_lat', 'checkin_lng',
        'travel_time_mins', 'onsite_time_mins',
        'checkin_photo', 'missed_photo', 'missed_reason', 'missed_status',
        'missed_reviewed_by', 'missed_review_comment', 'missed_reviewed_at',
    ];

    protected $casts = [
        'scheduled_date' => 'date',
        'started_at' => 'datetime',
        'completed_at' => 'datetime',
        'missed_reviewed_at' => 'datetime',
    ];

    // ── Relationships ──────────────────────────────────────────────────────────

    public function officer() { return $this->belongsTo(User::class, 'officer_id'); }
    public function institution() { return $this->belongsTo(Institution::class); }
    public function outcome() { return $this->hasOne(VisitOutcome::class); }
    public function sampleItems() { return $this->hasMany(SampleItem::class); }
    public function reviewedBy() { return $this->belongsTo(User::class, 'missed_reviewed_by'); }

    // ── Scopes ─────────────────────────────────────────────────────────────────

    public function scopeForOfficer($query, int $officerId) { return $query->where('officer_id', $officerId); }
    public function scopeToday($query) { return $query->whereDate('scheduled_date', today()); }
    public function scopePendingMissedReview($query) { return $query->where('status', 'missed')->where('missed_status', 'pending_review'); }

    // ── Helpers ────────────────────────────────────────────────────────────────

    public function isEditableToday(): bool
    {
        return $this->outcome?->editable_until?->isToday() ?? false;
    }

    public function applyPenalty(): void
    {
        // Deduct daily performance allowance from officer's current payroll record
        $month = $this->scheduled_date->startOfMonth();
        $payroll = PayrollLedger::firstOrCreate(
            ['officer_id' => $this->officer_id, 'month' => $month],
            [
                'basic_salary' => $this->officer->basic_salary,
                'security_deposit_held' => $this->officer->security_deposit,
                'performance_earned' => 0,
                'deductions' => 0,
                'deduction_reasons' => [],
            ]
        );

        $daily = $this->officer->performance_daily ?? 3000;
        $reasons = $payroll->deduction_reasons ?? [];
        $reasons[] = "Missed visit on {$this->scheduled_date->format('d M')} — reason rejected";

        $payroll->update([
            'deductions' => $payroll->deductions + $daily,
            'deduction_reasons' => $reasons,
        ]);
    }
}
