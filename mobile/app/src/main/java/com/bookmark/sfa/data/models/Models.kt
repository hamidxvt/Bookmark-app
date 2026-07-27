package com.bookmark.sfa.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

// ── Auth ──────────────────────────────────────────────────────────────────────

data class LoginRequest(val phone: String, val password: String)
data class ForgotPasswordRequest(val phone: String)
data class VerifyOtpRequest(val phone: String, val otp: String)
data class ResetPasswordRequest(val phone: String, val otp: String, val password: String, @SerializedName("password_confirmation") val passwordConfirmation: String)
data class ChangePasswordRequest(@SerializedName("current_password") val currentPassword: String, val password: String, @SerializedName("password_confirmation") val passwordConfirmation: String)

data class AuthResponse(val token: String, val user: UserProfile)
data class MessageResponse(val message: String, val success: Boolean = true)

// ── User ──────────────────────────────────────────────────────────────────────

data class UserProfile(
    val id: Long,
    val name: String,
    val phone: String,
    val email: String?,
    val role: String,
    val city: String?,
    val area: String?,
    @SerializedName("leave_sick_balance") val leaveSickBalance: Int,
    @SerializedName("leave_casual_balance") val leaveCasualBalance: Int,
    @SerializedName("annual_sample_limit") val annualSampleLimit: Double,
    @SerializedName("annual_sample_used") val annualSampleUsed: Double,
    @SerializedName("profile_image") val profileImage: String?
)

data class ProfileResponse(val user: UserProfile)

// ── Attendance ────────────────────────────────────────────────────────────────

data class LocationRequest(val latitude: Double, val longitude: Double)
data class CannotWorkRequest(val reason: String)

data class AttendanceResponse(
    val id: Long?,
    @SerializedName("day_start_at") val dayStartAt: String?,
    @SerializedName("day_end_at") val dayEndAt: String?,
    @SerializedName("is_started") val isStarted: Boolean
)

// ── Visits ────────────────────────────────────────────────────────────────────

@Parcelize
data class Visit(
    val id: Long,
    @SerializedName("institution_id") val institutionId: Long,
    @SerializedName("institution_name") val institutionName: String,
    @SerializedName("institution_address") val institutionAddress: String,
    @SerializedName("institution_type") val institutionType: String,
    val latitude: Double?,
    val longitude: Double?,
    val status: String,
    val source: String,
    val priority: String?,
    @SerializedName("route_order") val routeOrder: Int,
    @SerializedName("attempt_count") val attemptCount: Int,
    @SerializedName("scheduled_date") val scheduledDate: String,
    @SerializedName("coordinator_notes") val coordinatorNotes: String?
) : Parcelable

data class VisitListResponse(val visits: List<Visit>, @SerializedName("day_started") val dayStarted: Boolean)
data class VisitHistoryResponse(val visits: List<Visit>, val total: Int)
data class VisitResponse(val visit: Visit, val message: String?)
data class VisitListResponse2(val data: List<Visit>)

data class CreateVisitRequest(
    @SerializedName("institution_id") val institutionId: Long,
    @SerializedName("scheduled_date") val scheduledDate: String,
    @SerializedName("visit_type") val visitType: String = "fresh_visit"
)

data class CheckInRequest(val latitude: Double, val longitude: Double)
data class CheckInResponse(val success: Boolean, @SerializedName("distance_meters") val distanceMeters: Double, val message: String)

data class VisitOutcomeRequest(
    @SerializedName("contact_name") val contactName: String,
    val designation: String,
    @SerializedName("contact_phone") val contactPhone: String,
    @SerializedName("visit_type") val visitType: String,
    val notes: String,
    @SerializedName("followup_date") val followupDate: String?,
    val samples: List<SampleItem>?
)

data class SampleItem(
    @SerializedName("product_id") val productId: Long,
    val quantity: Int,
    val value: Double
)

@Parcelize
data class VisitDetail(
    val id: Long,
    @SerializedName("institution_name") val institutionName: String,
    @SerializedName("institution_address") val institutionAddress: String,
    val status: String,
    @SerializedName("contact_name") val contactName: String?,
    val designation: String?,
    @SerializedName("contact_phone") val contactPhone: String?,
    @SerializedName("visit_type") val visitType: String?,
    val notes: String?,
    @SerializedName("followup_date") val followupDate: String?,
    @SerializedName("travel_time_mins") val travelTimeMins: Int?,
    @SerializedName("onsite_time_mins") val onsiteTimeMins: Int?,
    @SerializedName("start_lat") val startLat: Double?,
    @SerializedName("start_lng") val startLng: Double?,
    @SerializedName("editable_until") val editableUntil: String?,
    val samples: List<SampleItem>?
) : Parcelable

data class VisitDetailResponse(val visit: VisitDetail)

// ── Products / Samples ────────────────────────────────────────────────────────

@Parcelize
data class Product(
    val id: Long,
    val name: String,
    val grade: String?,
    val subject: String?,
    val price: Double,
    val image: String?
) : Parcelable

data class ProductListResponse(val products: List<Product>)

data class SampleRequestBody(val items: List<SampleRequestItem>, @SerializedName("visit_id") val visitId: Long?)
data class SampleRequestItem(@SerializedName("product_id") val productId: Long, val quantity: Int)

@Parcelize
data class SampleRequest(
    val id: Long,
    val status: String,
    @SerializedName("total_pkr") val totalPkr: Double,
    @SerializedName("created_at") val createdAt: String,
    val items: List<SampleRequestItemDetail>?
) : Parcelable

data class SampleRequestItemDetail(
    @SerializedName("product_name") val productName: String,
    val quantity: Int,
    val value: Double
)

data class SampleRequestListResponse(val requests: List<SampleRequest>)

// ── Leave ─────────────────────────────────────────────────────────────────────

data class LeaveRequest(val date: String, val type: String, val reason: String)

data class LeaveBalanceResponse(
    @SerializedName("sick_balance") val sickBalance: Int,
    @SerializedName("casual_balance") val casualBalance: Int,
    val total: Int
)

data class LeaveRequestDetail(
    val id: Long,
    val date: String,
    val type: String,
    val status: String,
    val reason: String
)

data class LeaveRequestListResponse(val requests: List<LeaveRequestDetail>)

// ── Earnings ──────────────────────────────────────────────────────────────────

data class EarningsResponse(
    @SerializedName("basic_salary") val basicSalary: Double,
    @SerializedName("security_deposit_held") val securityDepositHeld: Double,
    @SerializedName("performance_earned") val performanceEarned: Double,
    val deductions: Double,
    @SerializedName("net_payout") val netPayout: Double,
    @SerializedName("working_days_completed") val workingDaysCompleted: Int,
    @SerializedName("deduction_reasons") val deductionReasons: List<String>?
)

// ── Performance ───────────────────────────────────────────────────────────────

data class PerformanceResponse(
    @SerializedName("total_visits") val totalVisits: Int,
    @SerializedName("completed_visits") val completedVisits: Int,
    @SerializedName("missed_visits") val missedVisits: Int,
    @SerializedName("weekly_target") val weeklyTarget: Int,
    @SerializedName("weekly_completed") val weeklyCompleted: Int,
    @SerializedName("sample_used") val sampleUsed: Double,
    @SerializedName("sample_limit") val sampleLimit: Double
)

// ── Engagement ────────────────────────────────────────────────────────────────

data class DailyContentResponse(
    val quote: String,
    val tip: String,
    @SerializedName("day_start_message") val dayStartMessage: String,
    @SerializedName("half_day_message") val halfDayMessage: String,
    @SerializedName("day_end_message") val dayEndMessage: String
)
