<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class PayrollLedger extends Model
{
    protected $fillable = [
        'officer_id', 'month',
        'basic_salary', 'security_deposit_held',
        'performance_earned', 'deductions', 'deduction_reasons',
    ];

    protected $casts = [
        'month' => 'date',
        'basic_salary' => 'decimal:2',
        'security_deposit_held' => 'decimal:2',
        'performance_earned' => 'decimal:2',
        'deductions' => 'decimal:2',
        'deduction_reasons' => 'array',
    ];

    public function officer() { return $this->belongsTo(User::class, 'officer_id'); }

    public function getNetPayoutAttribute(): float
    {
        return $this->basic_salary + $this->performance_earned - $this->deductions;
    }
}
