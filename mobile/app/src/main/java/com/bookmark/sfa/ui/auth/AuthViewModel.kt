package com.bookmark.sfa.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.local.SessionManager
import com.bookmark.sfa.data.models.*
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val api: ApiService,
    private val session: SessionManager
) : ViewModel() {

    private val _loginState = MutableLiveData<Resource<AuthResponse>>()
    val loginState: LiveData<Resource<AuthResponse>> = _loginState

    private val _forgotState = MutableLiveData<Resource<MessageResponse>>()
    val forgotState: LiveData<Resource<MessageResponse>> = _forgotState

    private val _otpState = MutableLiveData<Resource<MessageResponse>>()
    val otpState: LiveData<Resource<MessageResponse>> = _otpState

    private val _resetState = MutableLiveData<Resource<MessageResponse>>()
    val resetState: LiveData<Resource<MessageResponse>> = _resetState

    private val _changePassState = MutableLiveData<Resource<MessageResponse>>()
    val changePassState: LiveData<Resource<MessageResponse>> = _changePassState

    fun login(phone: String, password: String) = viewModelScope.launch {
        _loginState.value = Resource.Loading()
        try {
            val response = api.login(LoginRequest(phone, password))
            if (response.isSuccessful && response.body() != null) {
                val body = response.body()!!
                session.saveToken(body.token)
                session.saveUser(body.user.id, body.user.name, body.user.phone, body.user.role)
                _loginState.value = Resource.Success(body)
            } else {
                _loginState.value = Resource.Error("Invalid phone or password")
            }
        } catch (e: Exception) {
            _loginState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun forgotPassword(phone: String) = viewModelScope.launch {
        _forgotState.value = Resource.Loading()
        try {
            val response = api.forgotPassword(ForgotPasswordRequest(phone))
            if (response.isSuccessful) _forgotState.value = Resource.Success(response.body()!!)
            else _forgotState.value = Resource.Error("Phone number not found")
        } catch (e: Exception) {
            _forgotState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun verifyOtp(phone: String, otp: String) = viewModelScope.launch {
        _otpState.value = Resource.Loading()
        try {
            val response = api.verifyOtp(VerifyOtpRequest(phone, otp))
            if (response.isSuccessful) _otpState.value = Resource.Success(response.body()!!)
            else _otpState.value = Resource.Error("Invalid OTP")
        } catch (e: Exception) {
            _otpState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun resetPassword(phone: String, otp: String, password: String, confirm: String) = viewModelScope.launch {
        _resetState.value = Resource.Loading()
        try {
            val response = api.resetPassword(ResetPasswordRequest(phone, otp, password, confirm))
            if (response.isSuccessful) _resetState.value = Resource.Success(response.body()!!)
            else _resetState.value = Resource.Error("Failed to reset password")
        } catch (e: Exception) {
            _resetState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun changePassword(current: String, new: String, confirm: String) = viewModelScope.launch {
        _changePassState.value = Resource.Loading()
        try {
            val response = api.changePassword(ChangePasswordRequest(current, new, confirm))
            if (response.isSuccessful) _changePassState.value = Resource.Success(response.body()!!)
            else _changePassState.value = Resource.Error("Failed to change password")
        } catch (e: Exception) {
            _changePassState.value = Resource.Error(e.message ?: "Network error")
        }
    }
}
