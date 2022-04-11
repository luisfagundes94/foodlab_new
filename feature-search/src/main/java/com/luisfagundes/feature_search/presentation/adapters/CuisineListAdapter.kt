package com.luisfagundes.feature_search.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.luisfagundes.domain.model.cuisines
import com.luisfagundes.extensions.load
import com.luisfagundes.feature_search.databinding.LayoutCuisineItemBinding

class CuisineListAdapter: RecyclerView.Adapter<CuisineListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LayoutCuisineItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(cuisines[position])
    }

    override fun getItemCount() = cuisines.count()

    class ViewHolder(
        private val binding: LayoutCuisineItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cuisineName: String) = with(binding) {
            name.text = cuisineName
            image.load("https://rbl-prod-blog-bucket.s3.ap-south-1.amazonaws.com/wp-content/uploads/2019/12/17113725/5-hot-new-cuisines-mob.jpg")
        }
    }
}