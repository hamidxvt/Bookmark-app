package com.bookmark.sfa.ui.sample

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmark.sfa.databinding.ActivitySampleRequestBinding
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class SampleRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySampleRequestBinding
    private val viewModel: SampleViewModel by viewModels()
    private lateinit var adapter: SampleProductAdapter
    private val pkrFmt = NumberFormat.getNumberInstance(Locale.getDefault()).apply { maximumFractionDigits = 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySampleRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { finish() }

        adapter = SampleProductAdapter { product, qty ->
            viewModel.updateCartItem(product, qty)
        }
        binding.rvProducts.layoutManager = LinearLayoutManager(this)
        binding.rvProducts.adapter = adapter

        binding.btnSubmitRequest.setOnClickListener {
            if (viewModel.cartTotal.value == 0.0) { showToast("Add at least one product"); return@setOnClickListener }
            viewModel.submitSampleRequest(null)
        }

        observeViewModel()
        viewModel.loadProducts()
    }

    private fun observeViewModel() {
        viewModel.productsState.observe(this) { result ->
            if (result is Resource.Success) adapter.submitList(result.data?.products ?: emptyList())
        }

        viewModel.cartTotal.observe(this) { total ->
            binding.tvTotal.text = "Total: PKR ${pkrFmt.format(total)}"
        }

        viewModel.remainingBudget.observe(this) { remaining ->
            binding.tvBudget.text = "Remaining budget: PKR ${pkrFmt.format(remaining)}"
            binding.btnSubmitRequest.isEnabled = (remaining ?: 0.0) >= 0
        }

        viewModel.submitState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showToast("Sample request submitted for approval")
                    finish()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Request failed")
                }
            }
        }
    }
}
