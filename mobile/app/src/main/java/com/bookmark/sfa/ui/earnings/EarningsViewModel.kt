package com.bookmark.sfa.ui.earnings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bookmark.sfa.data.api.ApiService
import com.bookmark.sfa.data.models.EarningsResponse
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EarningsViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _earningsState = MutableLiveData<Resource<EarningsResponse>>()
    val earningsState: LiveData<Resource<EarningsResponse>> = _earningsState

    fun loadEarnings() = viewModelScope.launch {
        _earningsState.value = Resource.Loading()
        try {
            val r = api.getMyEarnings()
            if (r.isSuccessful) _earningsState.value = Resource.Success(r.body())
            else _earningsState.value = Resource.Error("Failed to load earnings")
        } catch (e: Exception) { _earningsState.value = Resource.Error(e.message) }
    }
}
