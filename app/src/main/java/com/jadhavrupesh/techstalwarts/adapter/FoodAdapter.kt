package com.jadhavrupesh.techstalwarts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadhavrupesh.techstalwarts.databinding.SingleItemLayoutBinding
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity
import javax.inject.Inject

class FoodAdapter @Inject constructor() : RecyclerView.Adapter<FoodAdapter.ViewHolder>() {

    var onPostClick: (FoodDetailsEntity) -> Unit = {}

    var items: MutableList<FoodDetailsEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[holder.adapterPosition]
        holder.binding.tvDesc.text = currentItem.name
        holder.binding.tvTitle.text = currentItem.description
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.binding.ivImage)
        holder.itemView.setOnClickListener {
            onPostClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: SingleItemLayoutBinding) : RecyclerView.ViewHolder(binding.root)
}