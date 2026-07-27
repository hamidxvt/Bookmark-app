package com.bookmark.sfa.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.models.DailyContentResponse
import com.bookmark.sfa.data.models.VisitListResponse
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _visitsState = MutableLiveData<Resource<VisitListResponse>>()
    val visitsState: LiveData<Resource<VisitListResponse>> = _visitsState

    private val _dailyContent = MutableLiveData<DailyContentResponse>()
    val dailyContent: LiveData<DailyContentResponse> = _dailyContent

    fun loadTodayVisits() = viewModelScope.launch {
        _visitsState.value = Resource.Loading()
        try {
            val response = api.getTodayVisits()
            if (response.isSuccessful) _visitsState.value = Resource.Success(response.body()!!)
            else _visitsState.value = Resource.Error("Failed to load visits")
        } catch (e: Exception) {
            _visitsState.value = Resource.Error(e.message ?: "Network error")
        }
    }

    fun loadDailyContent() = viewModelScope.launch {
        try {
            val response = api.getDailyContent()
            if (response.isSuccessful) _dailyContent.value = response.body()
        } catch (_: Exception) {}
    }
}
