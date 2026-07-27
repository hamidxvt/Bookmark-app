package com.bookmark.sfa.ui.visit

import android.content.ContentResolver
import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.models.*
import com.bookmark.sfa.utils.LocationHelper
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

@HiltViewModel
class VisitViewModel @Inject constructor(
    private val api: ApiService,
    private val locationHelper: LocationHelper
) : ViewModel() {

    private val _checkInState = MutableLiveData<Resource<CheckInResponse>>()
    val checkInState: LiveData<Resource<CheckInResponse>> = _checkInState

    private val _photoUploadState = MutableLiveData<Resource<MessageResponse>>()
    val photoUploadState: LiveData<Resource<MessageResponse>> = _photoUploadState

    private val _outcomeState = MutableLiveData<Resource<VisitResponse>>()
    val outcomeState: LiveData<Resource<VisitResponse>> = _outcomeState

    private val _visitDetailState = MutableLiveData<Resource<VisitDetailResponse>>()
    val visitDetailState: LiveData<Resource<VisitDetailResponse>> = _visitDetailState

    private val _missedState = MutableLiveData<Resource<MessageResponse>>()
    val missedState: LiveData<Resource<MessageResponse>> = _missedState

    fun startVisit(visitId: Long) = viewModelScope.launch {
        try { api.startVisit(visitId) } catch (_: Exception) {}
    }

    fun checkIn(visitId: Long, lat: Double, lng: Double) = viewModelScope.launch {
        _checkInState.value = Resource.Loading()
        try {
            val loc = locationHelper.getLastKnownLocation()
            val response = api.checkIn(visitId, CheckInRequest(loc?.latitude ?: lat, loc?.longitude ?: lng))
            if (response.isSuccessful) _checkInState.value = Resource.Success(response.body()!!)
            else _checkInState.value = Resource.Error("GPS check failed")
        } catch (e: Exception) {
            _checkInState.value = Resource.Error(e.message ?: "GPS error")
        }
    }

    fun uploadCheckInPhoto(visitId: Long, uri: Uri, contentResolver: ContentResolver) = viewModelScope.launch {
        _photoUploadState.value = Resource.Loading()
        try {
            val tempFile = File.createTempFile("checkin", ".jpg")
            contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(tempFile).use { output -> input.copyTo(output) }
            }
            val requestBody = tempFile.asRequestBody("image/jpeg".toMediaType())
            val part = MultipartBody.Part.createFormData("photo", tempFile.name, requestBody)
            val response = api.uploadCheckInPhoto(visitId, part)
            if (response.isSuccessful) _photoUploadState.value = Resource.Success(response.body()!!)
            else _photoUploadState.value = Resource.Error("Photo upload failed")
        } catch (e: Exception) {
            _photoUploadState.value = Resource.Error(e.message ?: "Upload error")
        }
    }

    fun saveOutcome(visitId: Long, request: VisitOutcomeRequest) = viewModelScope.launch {
        _outcomeState.value = Resource.Loading()
        try {
            val response = api.saveOutcome(visitId, request)
            if (response.isSuccessful) _outcomeState.value = Resource.Success(response.body()!!)
            else _outcomeState.value = Resource.Error("Failed to save outcome")
        } catch (e: Exception) {
            _outcomeState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun loadVisitDetail(visitId: Long) = viewModelScope.launch {
        _visitDetailState.value = Resource.Loading()
        try {
            val response = api.getVisitDetail(visitId)
            if (response.isSuccessful) _visitDetailState.value = Resource.Success(response.body()!!)
            else _visitDetailState.value = Resource.Error("Failed to load detail")
        } catch (e: Exception) {
            _visitDetailState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun missVisit(visitId: Long, reason: String, uri: Uri, contentResolver: ContentResolver) = viewModelScope.launch {
        _missedState.value = Resource.Loading()
        try {
            val tempFile = File.createTempFile("missed", ".jpg")
            contentResolver.openInputStream(uri)?.use { input ->
                FileOutputStream(tempFile).use { output -> input.copyTo(output) }
            }
            val photoPart = MultipartBody.Part.createFormData("photo", tempFile.name, tempFile.asRequestBody("image/jpeg".toMediaType()))
            val reasonBody = reason.toRequestBody("text/plain".toMediaType())
            val response = api.missVisit(visitId, photoPart, reasonBody)
            if (response.isSuccessful) _missedState.value = Resource.Success(response.body()!!)
            else _missedState.value = Resource.Error("Failed to submit")
        } catch (e: Exception) {
            _missedState.value = Resource.Error(e.message ?: "Network error")
        }
    }
}
