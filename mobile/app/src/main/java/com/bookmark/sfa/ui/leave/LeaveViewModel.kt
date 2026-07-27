package com.bookmark.sfa.ui.leave

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.models.*
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LeaveViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _balanceState = MutableLiveData<Resource<LeaveBalanceResponse>>()
    val balanceState: LiveData<Resource<LeaveBalanceResponse>> = _balanceState

    private val _applyState = MutableLiveData<Resource<MessageResponse>>()
    val applyState: LiveData<Resource<MessageResponse>> = _applyState

    private val _requestsState = MutableLiveData<Resource<LeaveRequestListResponse>>()
    val requestsState: LiveData<Resource<LeaveRequestListResponse>> = _requestsState

    fun loadLeaveBalance() = viewModelScope.launch {
        try {
            val r = api.getLeaveBalance()
            if (r.isSuccessful) _balanceState.value = Resource.Success(r.body())
        } catch (e: Exception) { _balanceState.value = Resource.Error(e.message) }
    }

    fun loadLeaveRequests() = viewModelScope.launch {
        try {
            val r = api.getMyLeaveRequests()
            if (r.isSuccessful) _requestsState.value = Resource.Success(r.body())
        } catch (_: Exception) {}
    }

    fun applyLeave(date: String, type: String, reason: String) = viewModelScope.launch {
        _applyState.value = Resource.Loading()
        try {
            val r = api.applyLeave(LeaveRequest(date, type, reason))
            if (r.isSuccessful) _applyState.value = Resource.Success(r.body())
            else _applyState.value = Resource.Error("Failed to apply for leave")
        } catch (e: Exception) { _applyState.value = Resource.Error(e.message) }
    }
}
