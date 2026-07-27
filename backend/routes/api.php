<?php

use Illuminate\Support\Facades\Route;
use App\Http\Controllers\AuthController;
use App\Http\Controllers\AttendanceController;
use App\Http\Controllers\VisitController;
use App\Http\Controllers\LocationController;
use App\Http\Controllers\SampleController;
use App\Http\Controllers\LeaveController;
use App\Http\Controllers\PayrollController;
use App\Http\Controllers\EngagementController;
use App\Http\Controllers\Admin\DashboardController;
use App\Http\Controllers\Admin\OfficerController;
use App\Http\Controllers\Admin\AdminVisitController;
use App\Http\Controllers\Admin\MissedVisitController;
use App\Http\Controllers\Admin\AdminSampleController;
use App\Http\Controllers\Admin\AdminLeaveController;
use App\Http\Controllers\Admin\AdminPayrollController;
use App\Http\Controllers\Admin\InstitutionController;
use App\Http\Controllers\Admin\MasterDataController;

// ── Public routes (no auth) ───────────────────────────────────────────────────
Route::post('/auth/login', [AuthController::class, 'login']);
Route::post('/auth/forgot-password', [AuthController::class, 'forgotPassword']);
Route::post('/auth/verify-otp', [AuthController::class, 'verifyOtp']);
Route::post('/auth/reset-password', [AuthController::class, 'resetPassword']);

// ── Authenticated routes ───────────────────────────────────────────────────────
Route::middleware('auth:sanctum')->group(function () {
    Route::post('/auth/logout', [AuthController::class, 'logout']);
    Route::post('/auth/change-password', [AuthController::class, 'changePassword']);
    Route::get('/profile', [AuthController::class, 'profile']);

    // Attendance
    Route::post('/attendance/start-day', [AttendanceController::class, 'startDay']);
    Route::post('/attendance/end-day', [AttendanceController::class, 'endDay']);
    Route::post('/attendance/cannot-work', [AttendanceController::class, 'cannotWork']);
    Route::get('/attendance/today', [AttendanceController::class, 'today']);

    // Visits (officer)
    Route::get('/visits/today', [VisitController::class, 'todayVisits']);
    Route::get('/visits/history', [VisitController::class, 'history']);
    Route::get('/visits/{id}', [VisitController::class, 'show']);
    Route::post('/visits', [VisitController::class, 'createAdHoc']);
    Route::post('/visits/{id}/start', [VisitController::class, 'start']);
    Route::post('/visits/{id}/checkin', [VisitController::class, 'checkIn']);
    Route::post('/visits/{id}/checkin-photo', [VisitController::class, 'uploadCheckInPhoto']);
    Route::put('/visits/{id}/outcome', [VisitController::class, 'saveOutcome']);
    Route::post('/visits/{id}/miss', [VisitController::class, 'markMissed']);

    // GPS location
    Route::post('/location/update', [LocationController::class, 'update']);

    // Samples (officer)
    Route::get('/samples/products', [SampleController::class, 'products']);
    Route::post('/samples/request', [SampleController::class, 'request']);
    Route::get('/samples/my-requests', [SampleController::class, 'myRequests']);
    Route::post('/samples/{id}/mark-recovered', [SampleController::class, 'markRecovered']);

    // Leave (officer)
    Route::post('/leaves/apply', [LeaveController::class, 'apply']);
    Route::get('/leaves/balance', [LeaveController::class, 'balance']);
    Route::get('/leaves/my-requests', [LeaveController::class, 'myRequests']);

    // Payroll / earnings (officer)
    Route::get('/payroll/my-earnings', [PayrollController::class, 'myEarnings']);

    // Performance
    Route::get('/performance/dashboard', [VisitController::class, 'performanceDashboard']);

    // Engagement (motivational content)
    Route::get('/engagement/daily-content', [EngagementController::class, 'dailyContent']);

    // ── Admin / City Head routes ─────────────────────────────────────────────
    Route::middleware('role:admin,city_head,coordinator')->prefix('admin')->group(function () {
        // Dashboard
        Route::get('/dashboard/stats', [DashboardController::class, 'stats']);

        // Officers
        Route::get('/officers', [OfficerController::class, 'index']);
        Route::post('/officers', [OfficerController::class, 'store']);
        Route::get('/officers/live-positions', [OfficerController::class, 'livePositions']);
        Route::get('/officers/{id}', [OfficerController::class, 'show']);
        Route::put('/officers/{id}', [OfficerController::class, 'update']);
        Route::post('/officers/{id}/reset-password', [OfficerController::class, 'resetPassword']);

        // Visits
        Route::get('/visits', [AdminVisitController::class, 'index']);
        Route::get('/visits/today', [AdminVisitController::class, 'today']);
        Route::get('/visits/{id}', [AdminVisitController::class, 'show']);
        Route::post('/visits', [AdminVisitController::class, 'store']);

        // Missed visits
        Route::get('/missed-visits/pending', [MissedVisitController::class, 'pending']);
        Route::post('/missed-visits/{id}/approve', [MissedVisitController::class, 'approve']);
        Route::post('/missed-visits/{id}/reject', [MissedVisitController::class, 'reject']);
        Route::post('/missed-visits/{id}/override', [MissedVisitController::class, 'override'])->middleware('role:admin');

        // Samples
        Route::get('/samples/requests', [AdminSampleController::class, 'requests']);
        Route::post('/samples/requests/{id}/approve', [AdminSampleController::class, 'approve']);
        Route::post('/samples/requests/{id}/reject', [AdminSampleController::class, 'reject']);
        Route::get('/samples/ledger', [AdminSampleController::class, 'ledger']);

        // Leaves
        Route::get('/leaves', [AdminLeaveController::class, 'index']);
        Route::post('/leaves/{id}/approve', [AdminLeaveController::class, 'approve']);
        Route::post('/leaves/{id}/reject', [AdminLeaveController::class, 'reject']);

        // Payroll
        Route::get('/payroll/ledger', [AdminPayrollController::class, 'ledger']);
        Route::get('/payroll/officers/{id}/summary', [AdminPayrollController::class, 'officerSummary']);

        // Institutions
        Route::get('/institutions', [InstitutionController::class, 'index']);
        Route::get('/institutions/{id}', [InstitutionController::class, 'show']);
        Route::get('/institutions/{id}/visit-history', [InstitutionController::class, 'visitHistory']);

        // Master data
        Route::get('/cities', [MasterDataController::class, 'cities']);
        Route::get('/areas', [MasterDataController::class, 'areas']);
        Route::get('/products', [MasterDataController::class, 'products']);
    });
});
