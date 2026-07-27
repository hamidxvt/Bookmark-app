package com.bookmark.sfa.ui.missed

import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookmark.sfa.data.models.Visit
import com.bookmark.sfa.databinding.ActivityMissedVisitBinding
import com.bookmark.sfa.ui.visit.VisitViewModel
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MissedVisitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMissedVisitBinding
    private val viewModel: VisitViewModel by viewModels()
    private lateinit var visit: Visit
    private var photoUri: Uri? = null

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success) {
            binding.ivEvidencePhoto.setImageURI(photoUri)
            binding.ivEvidencePhoto.visibility = View.VISIBLE
            binding.tvPhotoRequired.visibility = View.GONE
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMissedVisitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        visit = intent.getParcelableExtra("visit")!!
        binding.tvInstitutionName.text = visit.institutionName
        binding.tvDescription.text = "You are marking this visit as missed. A photo and written reason are required before you can continue."

        binding.btnTakePhoto.setOnClickListener {
            val file = java.io.File(cacheDir, "missed_${System.currentTimeMillis()}.jpg")
            photoUri = androidx.core.content.FileProvider.getUriForFile(this, "$packageName.provider", file)
            takePictureLauncher.launch(photoUri)
        }

        binding.btnSubmitMissed.setOnClickListener {
            val reason = binding.etReason.text.toString().trim()
            if (photoUri == null) { showToast("Photo evidence is required"); return@setOnClickListener }
            if (reason.length < 10) { showToast("Please write a detailed reason (min 10 characters)"); return@setOnClickListener }
            viewModel.missVisit(visit.id, reason, photoUri!!, contentResolver)
        }

        viewModel.missedState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.btnSubmitMissed.isEnabled = false
                }
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showToast("Missed visit submitted for review")
                    finish()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    binding.btnSubmitMissed.isEnabled = true
                    showToast(result.message ?: "Submission failed")
                }
            }
        }
    }
}
