<?php

namespace App\Http\Controllers;

use App\Models\Attendance;
use Illuminate\Http\Request;

class AttendanceController extends Controller
{
    public function startDay(Request $request)
    {
        $request->validate(['latitude' => 'required|numeric', 'longitude' => 'required|numeric']);

        $today = today();
        $existing = Attendance::where('officer_id', $request->user()->id)
            ->whereDate('date', $today)->first();

        if ($existing?->isStarted()) {
            return response()->json([
                'message' => 'Day already started',
                'is_started' => true,
                'day_start_at' => $existing->day_start_at,
            ]);
        }

        $attendance = Attendance::updateOrCreate(
            ['officer_id' => $request->user()->id, 'date' => $today],
            [
                'day_start_at' => now(),
                'day_start_lat' => $request->latitude,
                'day_start_lng' => $request->longitude,
                'status' => 'present',
            ]
        );

        return response()->json([
            'id' => $attendance->id,
            'day_start_at' => $attendance->day_start_at,
            'is_started' => true,
        ]);
    }

    public function endDay(Request $request)
    {
        $request->validate(['latitude' => 'required|numeric', 'longitude' => 'required|numeric']);

        $attendance = Attendance::where('officer_id', $request->user()->id)
            ->whereDate('date', today())->first();

        if (!$attendance || !$attendance->isStarted()) {
            return response()->json(['message' => 'Day not started'], 422);
        }

        $attendance->update([
            'day_end_at' => now(),
            'day_end_lat' => $request->latitude,
            'day_end_lng' => $request->longitude,
        ]);

        return response()->json([
            'id' => $attendance->id,
            'day_start_at' => $attendance->day_start_at,
            'day_end_at' => $attendance->day_end_at,
            'is_started' => false,
        ]);
    }

    public function cannotWork(Request $request)
    {
        $request->validate(['reason' => 'required|string|min:5']);

        Attendance::updateOrCreate(
            ['officer_id' => $request->user()->id, 'date' => today()],
            [
                'cannot_work_reason' => $request->reason,
                'status' => 'cannot_work',
            ]
        );

        return response()->json(['message' => 'Recorded. Your coordinator has been notified.']);
    }

    public function today(Request $request)
    {
        $attendance = Attendance::where('officer_id', $request->user()->id)
            ->whereDate('date', today())->first();

        return response()->json([
            'id' => $attendance?->id,
            'day_start_at' => $attendance?->day_start_at,
            'day_end_at' => $attendance?->day_end_at,
            'is_started' => $attendance?->isStarted() ?? false,
        ]);
    }
}
