package com.bookmark.sfa.ui.sample

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
class SampleViewModel @Inject constructor(private val api: ApiService) : ViewModel() {

    private val _productsState = MutableLiveData<Resource<ProductListResponse>>()
    val productsState: LiveData<Resource<ProductListResponse>> = _productsState

    private val _submitState = MutableLiveData<Resource<MessageResponse>>()
    val submitState: LiveData<Resource<MessageResponse>> = _submitState

    private val cartItems = mutableMapOf<Long, Pair<Product, Int>>()

    private val _cartTotal = MutableLiveData(0.0)
    val cartTotal: LiveData<Double> = _cartTotal

    private val _remainingBudget = MutableLiveData<Double?>()
    val remainingBudget: LiveData<Double?> = _remainingBudget

    private var sampleLimit = 0.0
    private var sampleUsed = 0.0

    fun loadProducts() = viewModelScope.launch {
        _productsState.value = Resource.Loading()
        try {
            val r = api.getSampleProducts()
            if (r.isSuccessful) _productsState.value = Resource.Success(r.body())
            else _productsState.value = Resource.Error("Failed to load products")
        } catch (e: Exception) { _productsState.value = Resource.Error(e.message) }

        // Also get profile for limits
        try {
            val p = api.getProfile()
            if (p.isSuccessful) {
                sampleLimit = p.body()?.user?.annualSampleLimit ?: 0.0
                sampleUsed = p.body()?.user?.annualSampleUsed ?: 0.0
                updateBudget()
            }
        } catch (_: Exception) {}
    }

    fun updateCartItem(product: Product, qty: Int) {
        if (qty <= 0) cartItems.remove(product.id)
        else cartItems[product.id] = Pair(product, qty)

        val total = cartItems.values.sumOf { (p, q) -> p.price * q }
        _cartTotal.value = total
        updateBudget()
    }

    private fun updateBudget() {
        val total = _cartTotal.value ?: 0.0
        val remaining = sampleLimit - sampleUsed - total
        _remainingBudget.value = remaining
    }

    fun submitSampleRequest(visitId: Long?) = viewModelScope.launch {
        if ((_remainingBudget.value ?: 0.0) < 0) {
            _submitState.value = Resource.Error("Request exceeds your annual sample limit")
            return@launch
        }
        _submitState.value = Resource.Loading()
        try {
            val items = cartItems.values.map { (p, q) -> SampleRequestItem(p.id, q) }
            val r = api.requestSamples(SampleRequestBody(items, visitId))
            if (r.isSuccessful) _submitState.value = Resource.Success(r.body())
            else _submitState.value = Resource.Error("Request failed")
        } catch (e: Exception) { _submitState.value = Resource.Error(e.message) }
    }
}
