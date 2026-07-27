<?php

namespace App\Http\Controllers;

use App\Models\Attendance;
use App\Models\Institution;
use App\Models\Visit;
use App\Models\VisitOutcome;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Storage;

class VisitController extends Controller
{
    public function todayVisits(Request $request)
    {
        $officer = $request->user();
        $attendance = $officer->todayAttendance();

        $visits = Visit::with('institution')
            ->forOfficer($officer->id)
            ->today()
            ->orderBy('route_order')
            ->get()
            ->map(fn($v) => $this->formatVisit($v));

        return response()->json([
            'visits' => $visits,
            'day_started' => $attendance?->isStarted() ?? false,
        ]);
    }

    public function history(Request $request)
    {
        $visits = Visit::with('institution')
            ->forOfficer($request->user()->id)
            ->where('status', 'completed')
            ->latest()
            ->paginate(20);

        return response()->json([
            'visits' => $visits->items(),
            'total' => $visits->total(),
        ]);
    }

    public function show(Request $request, int $id)
    {
        $visit = Visit::with(['institution', 'outcome'])->forOfficer($request->user()->id)->findOrFail($id);
        return response()->json(['visit' => $this->formatVisitDetail($visit)]);
    }

    public function createAdHoc(Request $request)
    {
        $request->validate([
            'institution_id' => 'required|exists:institutions,id',
            'scheduled_date' => 'required|date',
            'visit_type' => 'nullable|string',
        ]);

        // Ensure officer has started day
        $attendance = $request->user()->todayAttendance();
        if (!$attendance?->isStarted()) {
            return response()->json(['message' => 'Start your day before creating a visit'], 422);
        }

        $maxOrder = Visit::forOfficer($request->user()->id)->today()->max('route_order') ?? 0;

        $visit = Visit::create([
            'officer_id' => $request->user()->id,
            'institution_id' => $request->institution_id,
            'scheduled_date' => $request->scheduled_date,
            'status' => 'pending',
            'source' => 'adhoc',
            'route_order' => $maxOrder + 1,
            'attempt_count' => 1,
        ]);

        return response()->json(['visit' => $this->formatVisit($visit->load('institution'))]);
    }

    public function start(Request $request, int $id)
    {
        $visit = Visit::forOfficer($request->user()->id)->findOrFail($id);

        // Block if day not started
        $attendance = $request->user()->todayAttendance();
        if (!$attendance?->isStarted()) {
            return response()->json(['message' => 'Start your day first'], 422);
        }

        $visit->update(['status' => 'ongoing', 'started_at' => now()]);
        return response()->json(['visit' => $this->formatVisit($visit->load('institution'))]);
    }

    public function checkIn(Request $request, int $id)
    {
        $request->validate(['latitude' => 'required|numeric', 'longitude' => 'required|numeric']);

        $visit = Visit::with('institution')->forOfficer($request->user()->id)->findOrFail($id);
        $institution = $visit->institution;

        $distance = $this->haversineDistance(
            $request->latitude, $request->longitude,
            $institution->lat, $institution->lng
        );

        $isWithinRange = $distance <= 300; // 300 metres

        $visit->update([
            'checkin_lat' => $request->latitude,
            'checkin_lng' => $request->longitude,
        ]);

        return response()->json([
            'success' => $isWithinRange,
            'distance_meters' => round($distance),
            'message' => $isWithinRange ? 'Check-in verified' : 'Too far from destination',
        ]);
    }

    public function uploadCheckInPhoto(Request $request, int $id)
    {
        $request->validate(['photo' => 'required|image|max:5120']);

        $visit = Visit::forOfficer($request->user()->id)->findOrFail($id);
        $path = $request->file('photo')->store('checkin-photos', 'public');
        $visit->update(['checkin_photo' => $path]);

        return response()->json(['message' => 'Photo uploaded']);
    }

    public function saveOutcome(Request $request, int $id)
    {
        $request->validate([
            'contact_name' => 'required|string',
            'designation' => 'required|string',
            'contact_phone' => 'required|string',
            'visit_type' => 'required|string',
            'notes' => 'nullable|string',
            'followup_date' => 'nullable|date',
        ]);

        $visit = Visit::forOfficer($request->user()->id)->findOrFail($id);

        // Allow same-day editing; lock after midnight
        $outcome = $visit->outcome;
        if ($outcome && !$outcome->editable_until->isToday()) {
            return response()->json(['message' => 'Visit details can only be edited on the same day'], 422);
        }

        $outcomeData = [
            'contact_name' => $request->contact_name,
            'designation' => $request->designation,
            'contact_phone' => $request->contact_phone,
            'visit_type' => $request->visit_type,
            'notes' => $request->notes,
            'followup_date' => $request->followup_date,
            'editable_until' => today()->endOfDay(),
        ];

        VisitOutcome::updateOrCreate(['visit_id' => $id], $outcomeData);
        $visit->update(['status' => 'completed', 'completed_at' => now()]);

        // Update payroll: add daily performance for completed visit day
        $this->creditDailyPerformance($visit);

        // If a follow-up date was set, pre-schedule the next visit
        if ($request->followup_date) {
            Visit::create([
                'officer_id' => $visit->officer_id,
                'institution_id' => $visit->institution_id,
                'scheduled_date' => $request->followup_date,
                'status' => 'pending',
                'source' => 'followup',
                'route_order' => 99,
                'attempt_count' => 1,
            ]);
        }

        return response()->json(['visit' => $this->formatVisit($visit->load('institution'))]);
    }

    public function markMissed(Request $request, int $id)
    {
        $request->validate([
            'photo' => 'required|image|max:5120',
            'reason' => 'required|string|min:10',
        ]);

        $visit = Visit::forOfficer($request->user()->id)->findOrFail($id);
        $photoPath = $request->file('photo')->store('missed-photos', 'public');

        $visit->update([
            'status' => 'missed',
            'missed_photo' => $photoPath,
            'missed_reason' => $request->reason,
            'missed_status' => 'pending_review',
        ]);

        // Carry forward: create same visit for tomorrow with incremented attempt count
        Visit::create([
            'officer_id' => $visit->officer_id,
            'institution_id' => $visit->institution_id,
            'scheduled_date' => today()->addDay(),
            'status' => 'pending',
            'source' => 'carryforward',
            'route_order' => 99,
            'attempt_count' => $visit->attempt_count + 1,
        ]);

        return response()->json(['message' => 'Missed visit submitted for review']);
    }

    public function performanceDashboard(Request $request)
    {
        $officer = $request->user();
        $month = now()->startOfMonth();

        $total = Visit::forOfficer($officer->id)->whereMonth('scheduled_date', now()->month)->count();
        $completed = Visit::forOfficer($officer->id)->whereMonth('scheduled_date', now()->month)->where('status', 'completed')->count();
        $missed = Visit::forOfficer($officer->id)->whereMonth('scheduled_date', now()->month)->where('status', 'missed')->count();

        $weeklyTarget = 35; // 7/day × 5 days
        $weeklyCompleted = Visit::forOfficer($officer->id)
            ->whereBetween('scheduled_date', [now()->startOfWeek(), now()->endOfWeek()])
            ->where('status', 'completed')->count();

        return response()->json([
            'total_visits' => $total,
            'completed_visits' => $completed,
            'missed_visits' => $missed,
            'weekly_target' => $weeklyTarget,
            'weekly_completed' => $weeklyCompleted,
            'sample_used' => $officer->annual_sample_used ?? 0,
            'sample_limit' => $officer->annual_sample_limit ?? 0,
        ]);
    }

    // ── Helpers ─────────────────────────────────────────────────────────────────

    private function haversineDistance(float $lat1, float $lng1, float $lat2, float $lng2): float
    {
        $earth = 6371000;
        $dLat = deg2rad($lat2 - $lat1);
        $dLng = deg2rad($lng2 - $lng1);
        $a = sin($dLat / 2) ** 2 + cos(deg2rad($lat1)) * cos(deg2rad($lat2)) * sin($dLng / 2) ** 2;
        return $earth * 2 * atan2(sqrt($a), sqrt(1 - $a));
    }

    private function creditDailyPerformance(Visit $visit): void
    {
        $officer = $visit->officer;
        $month = $visit->scheduled_date->startOfMonth();

        // Only credit once per working day (not per visit)
        $alreadyCredited = Visit::where('officer_id', $officer->id)
            ->whereDate('scheduled_date', $visit->scheduled_date)
            ->where('status', 'completed')
            ->where('id', '!=', $visit->id)
            ->exists();

        if ($alreadyCredited) return;

        $payroll = \App\Models\PayrollLedger::firstOrCreate(
            ['officer_id' => $officer->id, 'month' => $month],
            [
                'basic_salary' => $officer->basic_salary ?? 0,
                'security_deposit_held' => $officer->security_deposit ?? 0,
                'performance_earned' => 0,
                'deductions' => 0,
                'deduction_reasons' => [],
            ]
        );

        $daily = $officer->performance_daily ?? 3000;
        $payroll->increment('performance_earned', $daily);
    }

    private function formatVisit(Visit $visit): array
    {
        return [
            'id' => $visit->id,
            'institution_id' => $visit->institution_id,
            'institution_name' => $visit->institution?->name,
            'institution_address' => $visit->institution?->address,
            'institution_type' => $visit->institution?->type,
            'latitude' => $visit->institution?->lat,
            'longitude' => $visit->institution?->lng,
            'status' => $visit->status,
            'source' => $visit->source,
            'priority' => $visit->institution?->priority,
            'route_order' => $visit->route_order,
            'attempt_count' => $visit->attempt_count,
            'scheduled_date' => $visit->scheduled_date?->format('Y-m-d'),
            'coordinator_notes' => $visit->coordinator_notes,
        ];
    }

    private function formatVisitDetail(Visit $visit): array
    {
        return array_merge($this->formatVisit($visit), [
            'contact_name' => $visit->outcome?->contact_name,
            'designation' => $visit->outcome?->designation,
            'contact_phone' => $visit->outcome?->contact_phone,
            'visit_type' => $visit->outcome?->visit_type,
            'notes' => $visit->outcome?->notes,
            'followup_date' => $visit->outcome?->followup_date?->format('Y-m-d'),
            'editable_until' => $visit->outcome?->editable_until?->toIso8601String(),
            'travel_time_mins' => $visit->travel_time_mins,
            'onsite_time_mins' => $visit->onsite_time_mins,
            'start_lat' => $visit->start_lat,
            'start_lng' => $visit->start_lng,
        ]);
    }
}
