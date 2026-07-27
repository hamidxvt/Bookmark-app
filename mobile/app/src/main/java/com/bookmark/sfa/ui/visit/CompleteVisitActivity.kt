package com.bookmark.sfa.ui.visit

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookmark.sfa.R
import com.bookmark.sfa.data.models.Visit
import com.bookmark.sfa.data.models.VisitOutcomeRequest
import com.bookmark.sfa.databinding.ActivityCompleteVisitBinding
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@AndroidEntryPoint
class CompleteVisitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCompleteVisitBinding
    private val viewModel: VisitViewModel by viewModels()
    private lateinit var visit: Visit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCompleteVisitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        visit = intent.getParcelableExtra("visit")!!
        binding.tvInstitutionName.text = visit.institutionName

        setupVisitTypeDropdown()
        setupFollowUpDatePicker()
        setupSaveButton()
        observeViewModel()

        // Load existing outcome if editing same-day
        viewModel.loadVisitDetail(visit.id)
    }

    private fun setupVisitTypeDropdown() {
        val visitTypes = resources.getStringArray(R.array.visit_types)
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, visitTypes)
        binding.actvVisitType.setAdapter(adapter)
    }

    private fun setupFollowUpDatePicker() {
        binding.etFollowupDate.setOnClickListener {
            val datePicker = com.google.android.material.datepicker.MaterialDatePicker.Builder.datePicker()
                .setTitleText("Select follow-up date")
                .build()
            datePicker.addOnPositiveButtonClickListener { selection ->
                val date = java.util.Date(selection)
                val fmt = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())
                binding.etFollowupDate.setText(fmt.format(date))
            }
            datePicker.show(supportFragmentManager, "date_picker")
        }
    }

    private fun setupSaveButton() {
        binding.btnSaveVisit.setOnClickListener {
            val contactName = binding.etContactName.text.toString().trim()
            val designation = binding.etDesignation.text.toString().trim()
            val phone = binding.etContactPhone.text.toString().trim()
            val visitType = binding.actvVisitType.text.toString()
            val notes = binding.etNotes.text.toString().trim()
            val followupDate = binding.etFollowupDate.text.toString().trim().ifEmpty { null }

            if (contactName.isEmpty()) { showToast("Contact name is required"); return@setOnClickListener }
            if (designation.isEmpty()) { showToast("Designation is required"); return@setOnClickListener }
            if (phone.isEmpty()) { showToast("Contact phone is required"); return@setOnClickListener }
            if (visitType.isEmpty()) { showToast("Select a visit type"); return@setOnClickListener }

            val request = VisitOutcomeRequest(
                contactName = contactName,
                designation = designation,
                contactPhone = phone,
                visitType = visitType,
                notes = notes,
                followupDate = followupDate,
                samples = null
            )
            viewModel.saveOutcome(visit.id, request)
        }
    }

    private fun observeViewModel() {
        viewModel.visitDetailState.observe(this) { result ->
            if (result is Resource.Success) {
                val detail = result.data?.visit ?: return@observe
                // Pre-fill if editing same day
                detail.contactName?.let { binding.etContactName.setText(it) }
                detail.designation?.let { binding.etDesignation.setText(it) }
                detail.contactPhone?.let { binding.etContactPhone.setText(it) }
                detail.visitType?.let { binding.actvVisitType.setText(it) }
                detail.notes?.let { binding.etNotes.setText(it) }
                detail.followupDate?.let { binding.etFollowupDate.setText(it) }
            }
        }

        viewModel.outcomeState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showToast("Visit saved")
                    finish()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Failed to save")
                }
            }
        }
    }
}
