<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Model;

class LeaveRequest extends Model
{
    protected $fillable = ['officer_id', 'date', 'type', 'status', 'reason', 'reviewed_by'];

    protected $casts = ['date' => 'date'];

    public function officer() { return $this->belongsTo(User::class, 'officer_id'); }
    public function reviewer() { return $this->belongsTo(User::class, 'reviewed_by'); }
}
