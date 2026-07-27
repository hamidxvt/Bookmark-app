package com.bookmark.sfa.data.api

import com.bookmark.sfa.data.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    // ── Auth ─────────────────────────────────────────────────────────────────
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): Response<AuthResponse>

    @POST("auth/forgot-password")
    suspend fun forgotPassword(@Body request: ForgotPasswordRequest): Response<MessageResponse>

    @POST("auth/verify-otp")
    suspend fun verifyOtp(@Body request: VerifyOtpRequest): Response<MessageResponse>

    @POST("auth/reset-password")
    suspend fun resetPassword(@Body request: ResetPasswordRequest): Response<MessageResponse>

    @POST("auth/change-password")
    suspend fun changePassword(@Body request: ChangePasswordRequest): Response<MessageResponse>

    // ── Profile ───────────────────────────────────────────────────────────────
    @GET("profile")
    suspend fun getProfile(): Response<ProfileResponse>

    // ── Attendance ────────────────────────────────────────────────────────────
    @POST("attendance/start-day")
    suspend fun startDay(@Body request: LocationRequest): Response<AttendanceResponse>

    @POST("attendance/end-day")
    suspend fun endDay(@Body request: LocationRequest): Response<AttendanceResponse>

    @POST("attendance/cannot-work")
    suspend fun cannotWork(@Body request: CannotWorkRequest): Response<MessageResponse>

    @GET("attendance/today")
    suspend fun getTodayAttendance(): Response<AttendanceResponse>

    // ── Visits ────────────────────────────────────────────────────────────────
    @GET("visits/today")
    suspend fun getTodayVisits(): Response<VisitListResponse>

    @GET("visits/history")
    suspend fun getVisitHistory(@Query("page") page: Int = 1): Response<VisitHistoryResponse>

    @POST("visits")
    suspend fun createAdHocVisit(@Body request: CreateVisitRequest): Response<VisitResponse>

    @POST("visits/{id}/start")
    suspend fun startVisit(@Path("id") visitId: Long): Response<VisitResponse>

    @POST("visits/{id}/checkin")
    suspend fun checkIn(
        @Path("id") visitId: Long,
        @Body request: CheckInRequest
    ): Response<CheckInResponse>

    @Multipart
    @POST("visits/{id}/checkin-photo")
    suspend fun uploadCheckInPhoto(
        @Path("id") visitId: Long,
        @Part photo: MultipartBody.Part
    ): Response<MessageResponse>

    @PUT("visits/{id}/outcome")
    suspend fun saveOutcome(
        @Path("id") visitId: Long,
        @Body request: VisitOutcomeRequest
    ): Response<VisitResponse>

    @GET("visits/{id}")
    suspend fun getVisitDetail(@Path("id") visitId: Long): Response<VisitDetailResponse>

    // ── Missed visit ──────────────────────────────────────────────────────────
    @Multipart
    @POST("visits/{id}/miss")
    suspend fun missVisit(
        @Path("id") visitId: Long,
        @Part photo: MultipartBody.Part,
        @Part("reason") reason: RequestBody
    ): Response<MessageResponse>

    // ── Live GPS ──────────────────────────────────────────────────────────────
    @POST("location/update")
    suspend fun updateLocation(@Body request: LocationRequest): Response<MessageResponse>

    // ── Samples ───────────────────────────────────────────────────────────────
    @GET("samples/products")
    suspend fun getSampleProducts(): Response<ProductListResponse>

    @POST("samples/request")
    suspend fun requestSamples(@Body request: SampleRequestBody): Response<MessageResponse>

    @GET("samples/my-requests")
    suspend fun getMySampleRequests(): Response<SampleRequestListResponse>

    @POST("samples/{id}/mark-recovered")
    suspend fun markSampleRecovered(@Path("id") sampleId: Long): Response<MessageResponse>

    // ── Leave ─────────────────────────────────────────────────────────────────
    @POST("leaves/apply")
    suspend fun applyLeave(@Body request: LeaveRequest): Response<MessageResponse>

    @GET("leaves/balance")
    suspend fun getLeaveBalance(): Response<LeaveBalanceResponse>

    @GET("leaves/my-requests")
    suspend fun getMyLeaveRequests(): Response<LeaveRequestListResponse>

    // ── Earnings ──────────────────────────────────────────────────────────────
    @GET("payroll/my-earnings")
    suspend fun getMyEarnings(): Response<EarningsResponse>

    // ── Performance ───────────────────────────────────────────────────────────
    @GET("performance/dashboard")
    suspend fun getPerformanceDashboard(): Response<PerformanceResponse>

    // ── Daily quote ───────────────────────────────────────────────────────────
    @GET("engagement/daily-content")
    suspend fun getDailyContent(): Response<DailyContentResponse>
}
