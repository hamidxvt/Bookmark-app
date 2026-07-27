package com.bookmark.sfa.ui.sample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bookmark.sfa.data.models.Product
import com.bookmark.sfa.databinding.ItemSampleProductBinding
import java.text.NumberFormat
import java.util.Locale

class SampleProductAdapter(private val onQtyChange: (Product, Int) -> Unit) :
    ListAdapter<Product, SampleProductAdapter.VH>(DiffCB()) {

    inner class VH(private val b: ItemSampleProductBinding) : RecyclerView.ViewHolder(b.root) {
        private var qty = 0
        fun bind(product: Product) {
            val fmt = NumberFormat.getNumberInstance(Locale.getDefault()).apply { maximumFractionDigits = 0 }
            b.tvProductName.text = product.name
            b.tvGrade.text = product.grade ?: ""
            b.tvSubject.text = product.subject ?: ""
            b.tvPrice.text = "PKR ${fmt.format(product.price)} each"
            b.tvQty.text = qty.toString()

            b.btnMinus.setOnClickListener {
                if (qty > 0) { qty--; b.tvQty.text = qty.toString(); onQtyChange(product, qty) }
            }
            b.btnPlus.setOnClickListener {
                qty++; b.tvQty.text = qty.toString(); onQtyChange(product, qty)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = VH(
        ItemSampleProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )
    override fun onBindViewHolder(holder: VH, position: Int) = holder.bind(getItem(position))
    class DiffCB : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(a: Product, b: Product) = a.id == b.id
        override fun areContentsTheSame(a: Product, b: Product) = a == b
    }
}
