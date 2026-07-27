package com.bookmark.sfa.ui.earnings

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bookmark.sfa.databinding.ActivityEarningsBinding
import com.bookmark.sfa.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.text.NumberFormat
import java.util.Locale

@AndroidEntryPoint
class EarningsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEarningsBinding
    private val viewModel: EarningsViewModel by viewModels()
    private val pkrFormat = NumberFormat.getNumberInstance(Locale.getDefault()).apply { maximumFractionDigits = 0 }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEarningsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivBack.setOnClickListener { finish() }

        viewModel.earningsState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    val e = result.data!!
                    binding.tvBasicSalary.text = "PKR ${pkrFormat.format(e.basicSalary)}"
                    binding.tvSecurityDeposit.text = "PKR ${pkrFormat.format(e.securityDepositHeld)} withheld"
                    binding.tvPerformanceEarned.text = "PKR ${pkrFormat.format(e.performanceEarned)}"
                    binding.tvDeductions.text = "- PKR ${pkrFormat.format(e.deductions)}"
                    binding.tvNetPayout.text = "PKR ${pkrFormat.format(e.netPayout)}"
                    binding.tvWorkingDays.text = "${e.workingDaysCompleted} working days completed"

                    if (!e.deductionReasons.isNullOrEmpty()) {
                        binding.tvDeductionReasons.visibility = View.VISIBLE
                        binding.tvDeductionReasons.text = e.deductionReasons.joinToString("\n") { "• $it" }
                    }
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                }
            }
        }

        viewModel.loadEarnings()
    }
}
