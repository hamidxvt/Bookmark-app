<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class VisitOutcome extends Model
{
    protected $fillable = [
        'visit_id', 'contact_name', 'designation', 'contact_phone',
        'visit_type', 'notes', 'followup_date', 'editable_until',
    ];

    protected $casts = [
        'followup_date' => 'date',
        'editable_until' => 'datetime',
    ];

    public function visit() { return $this->belongsTo(Visit::class); }
}
