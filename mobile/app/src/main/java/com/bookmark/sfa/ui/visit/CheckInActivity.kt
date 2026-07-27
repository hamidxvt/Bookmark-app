package com.bookmark.sfa.ui.visit

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.bookmark.sfa.data.models.Visit
import com.bookmark.sfa.databinding.ActivityCheckInBinding
import com.bookmark.sfa.service.LocationTrackingService
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

/**
 * 3-step check-in wizard:
 * Step 1 — GPS proximity validation (must be within 200m of destination)
 * Step 2 — Take a photo of the location/door
 * Step 3 — Upload photo + start the visit
 */
@AndroidEntryPoint
class CheckInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCheckInBinding
    private val viewModel: VisitViewModel by viewModels()
    private lateinit var visit: Visit
    private var photoUri: Uri? = null
    private var currentStep = 1

    private val takePictureLauncher = registerForActivityResult(ActivityResultContracts.TakePicture()) { success ->
        if (success && photoUri != null) {
            binding.ivCheckInPhoto.setImageURI(photoUri)
            binding.ivCheckInPhoto.visibility = View.VISIBLE
            binding.btnNextStep.isEnabled = true
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCheckInBinding.inflate(layoutInflater)
        setContentView(binding.root)

        visit = intent.getParcelableExtra("visit")!!
        binding.tvInstitutionName.text = visit.institutionName
        binding.tvAddress.text = visit.institutionAddress

        // Open navigation immediately on visit start
        openNavigation()
        startLocationService()
        viewModel.startVisit(visit.id)

        showStep(1)
        setupButtons()
        observeViewModel()
    }

    private fun showStep(step: Int) {
        currentStep = step
        binding.tvStepIndicator.text = "Step $step of 3"
        binding.layoutStep1.visibility = if (step == 1) View.VISIBLE else View.GONE
        binding.layoutStep2.visibility = if (step == 2) View.VISIBLE else View.GONE
        binding.layoutStep3.visibility = if (step == 3) View.VISIBLE else View.GONE
        binding.btnNextStep.isEnabled = step != 2
        binding.btnNextStep.text = if (step == 3) "Complete Check-In" else "Next"
    }

    private fun setupButtons() {
        binding.ivBack.setOnClickListener { finish() }

        binding.btnCheckGps.setOnClickListener {
            viewModel.checkIn(visit.id, 0.0, 0.0) // location helper provides coords in ViewModel
        }

        binding.btnTakePhoto.setOnClickListener {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1002)
                return@setOnClickListener
            }
            val file = java.io.File(cacheDir, "checkin_${System.currentTimeMillis()}.jpg")
            photoUri = androidx.core.content.FileProvider.getUriForFile(this, "$packageName.provider", file)
            takePictureLauncher.launch(photoUri)
        }

        binding.btnNextStep.setOnClickListener {
            when (currentStep) {
                1 -> showStep(2)
                2 -> if (photoUri != null) showStep(3) else showToast("Please take a photo")
                3 -> submitCheckIn()
            }
        }
    }

    private fun submitCheckIn() {
        val uri = photoUri ?: run { showToast("Photo required"); return }
        viewModel.uploadCheckInPhoto(visit.id, uri, contentResolver)
    }

    private fun observeViewModel() {
        viewModel.checkInState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (result.data?.success == true) {
                        binding.tvGpsStatus.text = "GPS Verified"
                        binding.btnNextStep.isEnabled = true
                    } else {
                        showToast("Too far from destination (${result.data?.distanceMeters?.toInt()}m away)")
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "GPS check failed")
                }
            }
        }

        viewModel.photoUploadState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    startActivity(Intent(this, CompleteVisitActivity::class.java).apply {
                        putExtra("visit", visit)
                    })
                    finish()
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Photo upload failed")
                }
            }
        }
    }

    private fun openNavigation() {
        if (visit.latitude != null && visit.longitude != null) {
            val uri = Uri.parse("google.navigation:q=${visit.latitude},${visit.longitude}")
            val intent = Intent(Intent.ACTION_VIEW, uri).apply { setPackage("com.google.android.apps.maps") }
            if (intent.resolveActivity(packageManager) != null) startActivity(intent)
        }
    }

    private fun startLocationService() {
        val serviceIntent = Intent(this, LocationTrackingService::class.java)
        startForegroundService(serviceIntent)
    }
}
