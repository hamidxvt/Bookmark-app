package com.bookmark.sfa.ui.home

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bookmark.sfa.databinding.ActivityHomeBinding
import com.bookmark.sfa.ui.attendance.AttendanceViewModel
import com.bookmark.sfa.ui.leave.LeaveActivity
import com.bookmark.sfa.ui.earnings.EarningsActivity
import com.bookmark.sfa.ui.sample.SampleRequestActivity
import com.bookmark.sfa.ui.visit.AddVisitActivity
import com.bookmark.sfa.ui.visit.CheckInActivity
import com.bookmark.sfa.ui.visit.VisitAdapter
import com.bookmark.sfa.utils.Resource
import com.bookmark.sfa.utils.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private val attendanceVm: AttendanceViewModel by viewModels()
    private val homeVm: HomeViewModel by viewModels()
    private lateinit var visitAdapter: VisitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        requestLocationPermission()
        setupVisitList()
        setupClickListeners()
        observeViewModels()

        attendanceVm.checkTodayAttendance()
        homeVm.loadTodayVisits()
        homeVm.loadDailyContent()
    }

    private fun setupVisitList() {
        visitAdapter = VisitAdapter { visit ->
            if (!attendanceVm.isDayStarted()) {
                showToast("Please start your day first")
                return@VisitAdapter
            }
            startActivity(Intent(this, CheckInActivity::class.java).apply {
                putExtra("visit", visit)
            })
        }
        binding.rvVisits.apply {
            layoutManager = LinearLayoutManager(this@HomeActivity)
            adapter = visitAdapter
        }
    }

    private fun setupClickListeners() {
        binding.btnStartEndDay.setOnClickListener {
            if (!attendanceVm.isDayStarted()) {
                attendanceVm.startDay()
            } else {
                attendanceVm.endDay()
            }
        }

        binding.btnAddVisit.setOnClickListener {
            if (!attendanceVm.isDayStarted()) { showToast("Start your day first"); return@setOnClickListener }
            startActivity(Intent(this, AddVisitActivity::class.java))
        }

        binding.ivLeave.setOnClickListener { startActivity(Intent(this, LeaveActivity::class.java)) }
        binding.ivEarnings.setOnClickListener { startActivity(Intent(this, EarningsActivity::class.java)) }
        binding.ivSamples.setOnClickListener { startActivity(Intent(this, SampleRequestActivity::class.java)) }
    }

    private fun observeViewModels() {
        attendanceVm.attendanceState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.progressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    updateDayButton(result.data?.isStarted ?: false)
                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    showToast(result.message ?: "Error")
                }
            }
        }

        homeVm.visitsState.observe(this) { result ->
            when (result) {
                is Resource.Loading -> binding.visitProgressBar.visibility = View.VISIBLE
                is Resource.Success -> {
                    binding.visitProgressBar.visibility = View.GONE
                    val data = result.data!!
                    updateDayButton(data.dayStarted)
                    visitAdapter.submitList(data.visits)
                    binding.tvVisitCount.text = "${data.visits.size} visits today"
                    binding.layoutNoVisits.visibility = if (data.visits.isEmpty()) View.VISIBLE else View.GONE
                }
                is Resource.Error -> {
                    binding.visitProgressBar.visibility = View.GONE
                    showToast(result.message ?: "Error loading visits")
                }
            }
        }

        homeVm.dailyContent.observe(this) { content ->
            binding.tvDailyQuote.text = "\u201c${content.quote}\u201d"
        }

        attendanceVm.motivationalMessage.observe(this) { msg ->
            if (msg.isNotEmpty()) showMotivationalDialog(msg)
        }
    }

    private fun updateDayButton(started: Boolean) {
        binding.btnStartEndDay.text = if (started) "End Day" else "Start Day"
        binding.btnStartEndDay.isSelected = started
        binding.layoutVisitGate.visibility = if (!started) View.VISIBLE else View.GONE
    }

    private fun showMotivationalDialog(message: String) {
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("Let's go!") { d, _ -> d.dismiss() }
            .show()
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION), 1001)
        }
    }

    override fun onResume() {
        super.onResume()
        homeVm.loadTodayVisits()
    }
}
