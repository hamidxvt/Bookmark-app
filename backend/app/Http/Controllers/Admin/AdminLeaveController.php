<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\LeaveRequest;
use Illuminate\Http\Request;

class AdminLeaveController extends Controller
{
    public function index(Request $request)
    {
        $query = LeaveRequest::with('officer');
        if ($request->user()->isCityHead()) {
            $query->whereHas('officer', fn($q) => $q->where('city_id', $request->user()->city_id));
        }
        $requests = $query->latest()->get()->map(fn($r) => [
            'id' => $r->id,
            'officer_name' => $r->officer?->name,
            'officer_id' => $r->officer_id,
            'date' => $r->date->format('Y-m-d'),
            'type' => $r->type,
            'status' => $r->status,
            'reason' => $r->reason,
        ]);
        return response()->json(['requests' => $requests]);
    }

    public function approve(Request $request, int $id)
    {
        $leave = LeaveRequest::findOrFail($id);
        $leave->update(['status' => 'approved', 'reviewed_by' => $request->user()->id]);

        // Deduct from leave balance
        $officer = $leave->officer;
        $field = $leave->type === 'sick' ? 'leave_sick_balance' : 'leave_casual_balance';
        $officer->decrement($field);

        return response()->json(['message' => 'Leave approved']);
    }

    public function reject(Request $request, int $id)
    {
        LeaveRequest::findOrFail($id)->update(['status' => 'rejected', 'reviewed_by' => $request->user()->id]);
        return response()->json(['message' => 'Leave rejected']);
    }
}
