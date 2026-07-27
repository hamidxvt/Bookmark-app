package com.bookmark.sfa.ui.attendance

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.local.SessionManager
import com.bookmark.sfa.data.models.AttendanceResponse
import com.bookmark.sfa.data.models.CannotWorkRequest
import com.bookmark.sfa.data.models.LocationRequest
import com.bookmark.sfa.data.models.MessageResponse
import com.bookmark.sfa.utils.LocationHelper
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceViewModel @Inject constructor(
    private val api: ApiService,
    private val session: SessionManager,
    private val locationHelper: LocationHelper
) : ViewModel() {

    private val _attendanceState = MutableLiveData<Resource<AttendanceResponse>>()
    val attendanceState: LiveData<Resource<AttendanceResponse>> = _attendanceState

    private val _cannotWorkState = MutableLiveData<Resource<MessageResponse>>()
    val cannotWorkState: LiveData<Resource<MessageResponse>> = _cannotWorkState

    private val _motivationalMessage = MutableLiveData<String>()
    val motivationalMessage: LiveData<String> = _motivationalMessage

    fun isDayStarted() = session.isDayStarted()

    fun checkTodayAttendance() = viewModelScope.launch {
        try {
            val response = api.getTodayAttendance()
            if (response.isSuccessful) {
                val body = response.body()!!
                session.setDayStarted(body.isStarted, body.dayStartAt)
                _attendanceState.value = Resource.Success(body)
            }
        } catch (_: Exception) {}
    }

    fun startDay() = viewModelScope.launch {
        _attendanceState.value = Resource.Loading()
        try {
            val loc = locationHelper.getLastKnownLocation()
            val response = api.startDay(LocationRequest(loc?.latitude ?: 0.0, loc?.longitude ?: 0.0))
            if (response.isSuccessful) {
                val body = response.body()!!
                session.setDayStarted(true, body.dayStartAt)
                _attendanceState.value = Resource.Success(body)
                loadDayStartMessage()
            } else {
                _attendanceState.value = Resource.Error("Failed to start day")
            }
        } catch (e: Exception) {
            _attendanceState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun endDay() = viewModelScope.launch {
        _attendanceState.value = Resource.Loading()
        try {
            val loc = locationHelper.getLastKnownLocation()
            val response = api.endDay(LocationRequest(loc?.latitude ?: 0.0, loc?.longitude ?: 0.0))
            if (response.isSuccessful) {
                session.setDayStarted(false)
                _attendanceState.value = Resource.Success(response.body()!!)
                loadDayEndMessage()
            } else {
                _attendanceState.value = Resource.Error("Failed to end day")
            }
        } catch (e: Exception) {
            _attendanceState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun cannotWork(reason: String) = viewModelScope.launch {
        _cannotWorkState.value = Resource.Loading()
        try {
            val response = api.cannotWork(CannotWorkRequest(reason))
            if (response.isSuccessful) _cannotWorkState.value = Resource.Success(response.body()!!)
            else _cannotWorkState.value = Resource.Error("Failed to submit")
        } catch (e: Exception) {
            _cannotWorkState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    private fun loadDayStartMessage() = viewModelScope.launch {
        try {
            val r = api.getDailyContent()
            if (r.isSuccessful) _motivationalMessage.value = r.body()?.dayStartMessage ?: ""
        } catch (_: Exception) {}
    }

    private fun loadDayEndMessage() = viewModelScope.launch {
        try {
            val r = api.getDailyContent()
            if (r.isSuccessful) _motivationalMessage.value = r.body()?.dayEndMessage ?: ""
        } catch (_: Exception) {}
    }
}
