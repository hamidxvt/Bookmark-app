<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class Attendance extends Model
{
    protected $fillable = [
        'officer_id', 'date',
        'day_start_at', 'day_start_lat', 'day_start_lng',
        'day_end_at', 'day_end_lat', 'day_end_lng',
        'cannot_work_reason', 'status',
    ];

    protected $casts = [
        'date' => 'date',
        'day_start_at' => 'datetime',
        'day_end_at' => 'datetime',
    ];

    public function officer() { return $this->belongsTo(User::class, 'officer_id'); }

    public function isStarted(): bool { return !is_null($this->day_start_at); }
}
