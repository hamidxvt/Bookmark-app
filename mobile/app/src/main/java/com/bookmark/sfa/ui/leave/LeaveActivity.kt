package com.bookmark.sfa.ui.leave

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmark.sfa.databinding.ActivityLeaveBinding
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LeaveActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeaveBinding
    private val viewModel: LeaveViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeaveBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { finish() }

        setupLeaveTypeDropdown()
        setupApplyButton()
        setupDatePicker()
        observeViewModel()

        viewModel.loadLeaveBalance()
        viewModel.loadLeaveRequests()
    }

    private fun setupLeaveTypeDropdown() {
        val types = arrayOf("Sick Leave", "Casual Leave")
        binding.actvLeaveType.setAdapter(ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, types))
    }

    private fun setupDatePicker() {
        binding.etLeaveDate.setOnClickListener {
            val picker = com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select leave date").build()
            picker.addOnPositiveButtonClickListener { sel ->
                val fmt = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                binding.etLeaveDate.setText(fmt.format(java.util.Date(sel)))
            }
            picker.show(supportFragmentManager, "leave_date")
        }
    }

    private fun setupApplyButton() {
        binding.btnApplyLeave.setOnClickListener {
            val date = binding.etLeaveDate.text.toString().trim()
            val type = if (binding.actvLeaveType.text.toString().contains("Sick")) "sick" else "casual"
            val reason = binding.etLeaveReason.text.toString().trim()
            if (date.isEmpty()) { showToast("Select a date"); return@setOnClickListener }
            if (reason.isEmpty()) { showToast("Reason is required"); return@setOnClickListener }
            viewModel.applyLeave(date, type, reason)
        }
    }

    private fun observeViewModel() {
        viewModel.balanceState.observe(this) { result ->
            if (result is Resource.Success) {
                val b = result.data!!
                binding.tvSickBalance.text = "${b.sickBalance} / 10"
                binding.tvCasualBalance.text = "${b.casualBalance} / 18"
                binding.tvTotalBalance.text = "${b.total} / 28 days remaining"
            }
        }

        viewModel.applyState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showToast("Leave application submitted")
                    viewModel.loadLeaveBalance()
                    viewModel.loadLeaveRequests()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Failed to apply")
                }
            }
        }
    }
}
