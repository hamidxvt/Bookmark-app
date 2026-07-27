<?php

namespace App\Http\Controllers\Admin;

use App\Http\Controllers\Controller;
use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Str;

class OfficerController extends Controller
{
    public function index(Request $request)
    {
        $query = User::with('city', 'area')
            ->whereIn('role', ['officer', 'coordinator', 'city_head']);

        if ($request->user()->isCityHead()) {
            $query->where('city_id', $request->user()->city_id);
        }

        return response()->json(['officers' => $query->get()]);
    }

    public function store(Request $request)
    {
        $request->validate([
            'name' => 'required|string',
            'phone' => 'required|unique:users,phone',
            'role' => 'required|in:officer,coordinator,city_head,admin',
            'city_id' => 'nullable|exists:cities,id',
            'area_id' => 'nullable|exists:areas,id',
            'basic_salary' => 'nullable|numeric',
            'security_deposit' => 'nullable|numeric',
            'performance_daily' => 'nullable|numeric',
            'annual_sample_limit' => 'nullable|numeric',
        ]);

        $password = Str::random(8);

        $user = User::create(array_merge($request->only([
            'name', 'phone', 'email', 'role', 'city_id', 'area_id',
            'basic_salary', 'security_deposit', 'performance_daily', 'annual_sample_limit',
        ]), [
            'password' => Hash::make($password),
            'leave_sick_balance' => 10,
            'leave_casual_balance' => 18,
            'is_active' => true,
        ]));

        return response()->json([
            'message' => 'Officer created',
            'officer' => $user->load('city', 'area'),
            'temp_password' => $password, // Share with officer via SMS/WhatsApp
        ], 201);
    }

    public function show(int $id)
    {
        return response()->json(['officer' => User::with('city', 'area')->findOrFail($id)]);
    }

    public function update(Request $request, int $id)
    {
        $user = User::findOrFail($id);
        $user->update($request->only([
            'name', 'phone', 'email', 'role', 'city_id', 'area_id',
            'basic_salary', 'security_deposit', 'performance_daily', 'annual_sample_limit',
        ]));
        return response()->json(['message' => 'Updated', 'officer' => $user->load('city', 'area')]);
    }

    public function resetPassword(int $id)
    {
        $user = User::findOrFail($id);
        $newPassword = Str::random(8);
        $user->update(['password' => Hash::make($newPassword)]);
        // TODO: Notify officer via SMS
        return response()->json(['message' => 'Password reset', 'temp_password' => $newPassword]);
    }

    public function livePositions()
    {
        $officers = User::where('role', 'officer')
            ->whereNotNull('last_lat')
            ->where('last_location_at', '>=', now()->subMinutes(10))
            ->get()
            ->map(fn($o) => [
                'id' => $o->id,
                'name' => $o->name,
                'lat' => $o->last_lat,
                'lng' => $o->last_lng,
                'last_updated' => $o->last_location_at,
                'current_visit' => $o->visits()->where('status', 'ongoing')->first()?->institution?->name,
            ]);

        return response()->json(['officers' => $officers]);
    }
}
