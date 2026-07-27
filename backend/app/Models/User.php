<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Foundation\Auth\User as Authenticatable;
use Illuminate\Notifications\Notifiable;
use Laravel\Sanctum\HasApiTokens;

class User extends Authenticatable
{
    use HasApiTokens, HasFactory, Notifiable;

    protected $fillable = [
        'name', 'phone', 'email', 'password', 'role',
        'city_id', 'area_id', 'city_head_id',
        'basic_salary', 'security_deposit', 'performance_daily',
        'annual_sample_limit', 'annual_sample_used',
        'leave_sick_balance', 'leave_casual_balance',
        'profile_image', 'last_lat', 'last_lng', 'last_location_at',
        'is_active',
    ];

    protected $hidden = ['password', 'remember_token'];

    protected $casts = [
        'password' => 'hashed',
        'is_active' => 'boolean',
        'basic_salary' => 'decimal:2',
        'security_deposit' => 'decimal:2',
        'performance_daily' => 'decimal:2',
        'annual_sample_limit' => 'decimal:2',
        'annual_sample_used' => 'decimal:2',
    ];

    // ── Relationships ──────────────────────────────────────────────────────────

    public function city() { return $this->belongsTo(City::class); }
    public function area() { return $this->belongsTo(Area::class); }
    public function cityHead() { return $this->belongsTo(User::class, 'city_head_id'); }
    public function visits() { return $this->hasMany(Visit::class, 'officer_id'); }
    public function attendances() { return $this->hasMany(Attendance::class, 'officer_id'); }
    public function sampleRequests() { return $this->hasMany(SampleRequest::class, 'officer_id'); }
    public function leaveRequests() { return $this->hasMany(LeaveRequest::class, 'officer_id'); }
    public function payrollRecords() { return $this->hasMany(PayrollLedger::class, 'officer_id'); }

    // ── Helpers ────────────────────────────────────────────────────────────────

    public function isAdmin(): bool { return $this->role === 'admin'; }
    public function isCityHead(): bool { return $this->role === 'city_head'; }
    public function isCoordinator(): bool { return $this->role === 'coordinator'; }
    public function isOfficer(): bool { return $this->role === 'officer'; }

    public function remainingSampleBudget(): float
    {
        return ($this->annual_sample_limit ?? 0) - ($this->annual_sample_used ?? 0);
    }

    public function totalLeaveBalance(): int
    {
        return ($this->leave_sick_balance ?? 10) + ($this->leave_casual_balance ?? 18);
    }

    public function todayAttendance()
    {
        return $this->attendances()->whereDate('date', today())->first();
    }
}
