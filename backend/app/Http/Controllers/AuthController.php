<?php

namespace App\Http\Controllers;

use App\Models\User;
use Illuminate\Http\Request;
use Illuminate\Support\Facades\Cache;
use Illuminate\Support\Facades\Hash;

class AuthController extends Controller
{
    public function login(Request $request)
    {
        $request->validate(['phone' => 'required', 'password' => 'required']);

        $user = User::where('phone', $request->phone)->where('is_active', true)->first();

        if (!$user || !Hash::check($request->password, $user->password)) {
            return response()->json(['message' => 'Invalid credentials'], 401);
        }

        $token = $user->createToken('mobile-app')->plainTextToken;

        return response()->json([
            'token' => $token,
            'user' => $this->formatUser($user),
        ]);
    }

    public function logout(Request $request)
    {
        $request->user()->currentAccessToken()->delete();
        return response()->json(['message' => 'Logged out']);
    }

    public function profile(Request $request)
    {
        return response()->json(['user' => $this->formatUser($request->user())]);
    }

    public function forgotPassword(Request $request)
    {
        $request->validate(['phone' => 'required']);
        $user = User::where('phone', $request->phone)->first();
        if (!$user) return response()->json(['message' => 'Phone not found'], 404);

        $otp = rand(1000, 9999);
        Cache::put("otp:{$request->phone}", $otp, now()->addMinutes(10));

        // TODO: Send OTP via SMS gateway
        // For dev, return OTP in response (remove in production)
        return response()->json(['message' => 'OTP sent', 'dev_otp' => $otp]);
    }

    public function verifyOtp(Request $request)
    {
        $request->validate(['phone' => 'required', 'otp' => 'required']);
        $cached = Cache::get("otp:{$request->phone}");

        if (!$cached || $cached != $request->otp) {
            return response()->json(['message' => 'Invalid or expired OTP'], 422);
        }

        return response()->json(['message' => 'OTP verified']);
    }

    public function resetPassword(Request $request)
    {
        $request->validate([
            'phone' => 'required',
            'otp' => 'required',
            'password' => 'required|min:6|confirmed',
        ]);

        $cached = Cache::get("otp:{$request->phone}");
        if (!$cached || $cached != $request->otp) {
            return response()->json(['message' => 'Invalid or expired OTP'], 422);
        }

        $user = User::where('phone', $request->phone)->first();
        if (!$user) return response()->json(['message' => 'User not found'], 404);

        $user->update(['password' => Hash::make($request->password)]);
        Cache::forget("otp:{$request->phone}");

        return response()->json(['message' => 'Password reset successfully']);
    }

    public function changePassword(Request $request)
    {
        $request->validate([
            'current_password' => 'required',
            'password' => 'required|min:6|confirmed',
        ]);

        $user = $request->user();
        if (!Hash::check($request->current_password, $user->password)) {
            return response()->json(['message' => 'Current password is incorrect'], 422);
        }

        $user->update(['password' => Hash::make($request->password)]);
        return response()->json(['message' => 'Password changed successfully']);
    }

    private function formatUser(User $user): array
    {
        return [
            'id' => $user->id,
            'name' => $user->name,
            'phone' => $user->phone,
            'email' => $user->email,
            'role' => $user->role,
            'city' => $user->city?->name,
            'area' => $user->area?->name,
            'city_id' => $user->city_id,
            'area_id' => $user->area_id,
            'leave_sick_balance' => $user->leave_sick_balance ?? 10,
            'leave_casual_balance' => $user->leave_casual_balance ?? 18,
            'annual_sample_limit' => $user->annual_sample_limit ?? 0,
            'annual_sample_used' => $user->annual_sample_used ?? 0,
            'profile_image' => $user->profile_image,
        ];
    }
}
