<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class SampleRequest extends Model
{
    protected $fillable = [
        'officer_id', 'visit_id', 'status', 'total_pkr',
        'approved_by', 'approved_at',
        'reminder_10_sent_at', 'reminder_20_sent_at',
        'deducted_from_payroll', 'deducted_at',
    ];

    protected $casts = [
        'approved_at' => 'datetime',
        'reminder_10_sent_at' => 'datetime',
        'reminder_20_sent_at' => 'datetime',
        'deducted_at' => 'datetime',
        'deducted_from_payroll' => 'boolean',
        'total_pkr' => 'decimal:2',
    ];

    public function officer() { return $this->belongsTo(User::class, 'officer_id'); }
    public function items() { return $this->hasMany(SampleItem::class); }
    public function approvedBy() { return $this->belongsTo(User::class, 'approved_by'); }
}
