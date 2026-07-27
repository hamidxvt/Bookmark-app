package com.bookmark.sfa.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookmark.sfa.databinding.ActivityResetPasswordBinding
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPasswordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResetPasswordBinding
    private val viewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResetPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone = intent.getStringExtra("phone") ?: ""
        val otp = intent.getStringExtra("otp") ?: ""

        binding.btnReset.setOnClickListener {
            val password = binding.etPassword.text.toString()
            val confirm = binding.etConfirmPassword.text.toString()
            if (password.length < 6) { showToast("Password must be at least 6 characters"); return@setOnClickListener }
            if (password != confirm) { showToast("Passwords do not match"); return@setOnClickListener }
            viewModel.resetPassword(phone, otp, password, confirm)
        }

        viewModel.resetState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    showToast("Password reset successfully")
                    startActivity(Intent(this, LoginActivity::class.java).apply {
                        flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    })
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Reset failed")
                }
            }
        }
    }
}
