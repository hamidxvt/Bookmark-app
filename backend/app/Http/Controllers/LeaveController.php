<?php

namespace App\Http\Controllers;

use App\Models\LeaveRequest;
use Illuminate\Http\Request;

class LeaveController extends Controller
{
    public function apply(Request $request)
    {
        $request->validate([
            'date' => 'required|date|after_or_equal:today',
            'type' => 'required|in:sick,casual',
            'reason' => 'required|string',
        ]);

        $officer = $request->user();
        $balanceField = $request->type === 'sick' ? 'leave_sick_balance' : 'leave_casual_balance';

        if (($officer->$balanceField ?? 0) <= 0) {
            return response()->json(['message' => "No {$request->type} leave days remaining"], 422);
        }

        // Check for duplicate leave request
        $existing = LeaveRequest::where('officer_id', $officer->id)->where('date', $request->date)->first();
        if ($existing) {
            return response()->json(['message' => 'Leave already applied for this date'], 422);
        }

        LeaveRequest::create([
            'officer_id' => $officer->id,
            'date' => $request->date,
            'type' => $request->type,
            'status' => 'pending',
            'reason' => $request->reason,
        ]);

        return response()->json(['message' => 'Leave application submitted']);
    }

    public function balance(Request $request)
    {
        $officer = $request->user();
        return response()->json([
            'sick_balance' => $officer->leave_sick_balance ?? 10,
            'casual_balance' => $officer->leave_casual_balance ?? 18,
            'total' => ($officer->leave_sick_balance ?? 10) + ($officer->leave_casual_balance ?? 18),
        ]);
    }

    public function myRequests(Request $request)
    {
        $requests = LeaveRequest::where('officer_id', $request->user()->id)->latest()->get();
        return response()->json(['requests' => $requests]);
    }
}
