package com.bookmark.sfa.ui.visit

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookmark.sfa.R
import com.bookmark.sfa.data.models.Visit
import com.bookmark.sfa.databinding.ItemVisitBinding

class VisitAdapter(private val onClick: (Visit) -> Unit) :
    ListAdapter<Visit, VisitAdapter.VH>(DiffCB()) {

    inner class VH(private val binding: ItemVisitBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(visit: Visit) {
            binding.tvInstitutionName.text = visit.institutionName
            binding.tvAddress.text = visit.institutionAddress
            binding.tvRouteOrder.text = "#${visit.routeOrder}"
            binding.tvType.text = visit.institutionType.replaceFirstChar { it.uppercase() }

            val priorityColor = when (visit.priority?.lowercase()) {
                "high" -> ContextCompat.getColor(binding.root.context, R.color.priority_high)
                "medium" -> ContextCompat.getColor(binding.root.context, R.color.priority_medium)
                else -> ContextCompat.getColor(binding.root.context, R.color.priority_low)
            }
            binding.viewPriorityDot.setBackgroundColor(priorityColor)

            if (visit.attemptCount > 1) {
                binding.tvAttempt.visibility = android.view.View.VISIBLE
                binding.tvAttempt.text = "Attempt ${visit.attemptCount}"
            } else {
                binding.tvAttempt.visibility = android.view.View.GONE
            }

            if (!visit.coordinatorNotes.isNullOrEmpty()) {
                binding.tvCoordinatorNote.visibility = android.view.View.VISIBLE
                binding.tvCoordinatorNote.text = visit.coordinatorNotes
            } else {
                binding.tvCoordinatorNote.visibility = android.view.View.GONE
            }

            binding.root.setOnClickListener { onClick(visit) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemVisitBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))

    class DiffCB : DiffUtil.ItemCallback<Visit>() {
        override fun areItemsTheSame(a: Visit, b: Visit) = a.id == b.id
        override fun areContentsTheSame(a: Visit, b: Visit) = a == b
    }
}
