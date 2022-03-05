package com.losandroides.learn.presentation.provisional

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.losandroides.learn.databinding.ItemProvisionalBinding
import com.losandroides.learn.domain.model.Item

@Deprecated("This has to be replaced by Jetpack Compose stuff")
class ProvisionalItemAdapter(
    private val items: List<Item>
) : RecyclerView.Adapter<ProvisionalItemAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding = ItemProvisionalBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        val point = items[position]
        holder.bind(point)
    }

    override fun getItemCount() = items.size

    class ViewHolder(private val binding: ItemProvisionalBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(point: Item) {
            binding.tvTitle.text = point.title
        }
    }
}
