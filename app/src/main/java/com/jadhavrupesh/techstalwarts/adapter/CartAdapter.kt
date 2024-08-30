package com.jadhavrupesh.techstalwarts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jadhavrupesh.techstalwarts.adapter.FoodAdapter.ViewHolder
import com.jadhavrupesh.techstalwarts.databinding.SingleCartItemLayoutBinding
import com.jadhavrupesh.techstalwarts.model.CartItemEntity
import javax.inject.Inject

class CartAdapter @Inject constructor() : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    var onUpdate: (CartItemEntity) -> Unit = {}

    var items: MutableList<CartItemEntity> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder {
        val binding = SingleCartItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = items[holder.adapterPosition]
        holder.binding.tvPrice.text = "₹ ${currentItem.price * currentItem.quantity}"
        holder.binding.tvTitle.text = currentItem.name
        Glide.with(holder.itemView.context).load(currentItem.imageUrl).into(holder.binding.ivImage)
        holder.binding.numberPicker.value = currentItem.quantity
        holder.binding.numberPicker.setValueChangedListener { value, action ->
            onUpdate(
                currentItem.copy(
                    quantity = value
                )
            )
            holder.binding.tvPrice.text = "₹ ${currentItem.price * value}"
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val binding: SingleCartItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root)

}