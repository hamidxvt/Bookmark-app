<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\Attendance;
use App\Models\LeaveRequest;
use App\Models\SampleRequest;
use App\Models\User;
use App\Models\Visit;
use Illuminate\Http\Request;

class DashboardController extends Controller
{
    public function stats(Request $request)
    {
        $query = fn($model) => $request->user()->isCityHead()
            ? $model::whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id))
            : $model::query();

        $totalOfficers = User::where('role', 'officer')->count();
        $officersOnline = User::where('role', 'officer')
            ->whereNotNull('last_lat')
            ->where('last_location_at', '>=', now()->subMinutes(15))
            ->count();

        $visitsToday = Visit::whereDate('scheduled_date', today())->count();
        $completedToday = Visit::whereDate('scheduled_date', today())->where('status', 'completed')->count();
        $pendingMissed = Visit::where('status', 'missed')->where('missed_status', 'pending_review')->count();
        $pendingSamples = SampleRequest::where('status', 'pending')->count();
        $pendingLeaves = LeaveRequest::where('status', 'pending')->count();

        return response()->json([
            'total_officers' => $totalOfficers,
            'officers_online' => $officersOnline,
            'visits_today' => $visitsToday,
            'visits_completed_today' => $completedToday,
            'pending_missed_reviews' => $pendingMissed,
            'pending_sample_requests' => $pendingSamples,
            'pending_leave_requests' => $pendingLeaves,
        ]);
    }
}
