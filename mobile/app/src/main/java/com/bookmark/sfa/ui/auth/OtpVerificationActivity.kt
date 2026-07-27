package com.bookmark.sfa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookmark.sfa.databinding.ActivityOtpVerificationBinding
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OtpVerificationActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOtpVerificationBinding
    private val viewModel: AuthViewModel by viewModels()
    private var phone = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOtpVerificationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        phone = intent.getStringExtra("phone") ?: ""
        binding.tvPhone.text = "OTP sent to $phone"

        binding.btnVerify.setOnClickListener {
            val otp = binding.etOtp.text.toString().trim()
            if (otp.length < 4) { showToast("Enter the OTP"); return@setOnClickListener }
            viewModel.verifyOtp(phone, otp)
        }

        viewModel.otpState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    startActivity(Intent(this, ResetPasswordActivity::class.java).apply {
                        putExtra("phone", phone)
                        putExtra("otp", binding.etOtp.text.toString().trim())
                    })
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Invalid OTP")
                }
            }
        }
    }
}
