<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Institution extends Model
{
    use HasFactory;

    protected $fillable = [
        'area_id', 'name', 'type', 'priority',
        'address', 'lat', 'lng',
        'contact_name', 'contact_phone', 'contact_designation',
    ];

    protected $casts = ['lat' => 'decimal:8', 'lng' => 'decimal:8'];

    public function area() { return $this->belongsTo(Area::class); }
    public function visits() { return $this->hasMany(Visit::class); }

    public function lastVisitDate(): ?string
    {
        return $this->visits()->where('status', 'completed')->latest('completed_at')->value('scheduled_date');
    }

    public function daysSinceLastVisit(): int
    {
        $last = $this->lastVisitDate();
        return $last ? now()->diffInDays($last) : 9999;
    }
}
