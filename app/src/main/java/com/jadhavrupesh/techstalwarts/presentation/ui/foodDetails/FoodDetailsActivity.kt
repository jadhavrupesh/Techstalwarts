package com.jadhavrupesh.techstalwarts.presentation.ui.foodDetails

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.jadhavrupesh.techstalwarts.MainActivity
import com.jadhavrupesh.techstalwarts.R
import com.jadhavrupesh.techstalwarts.databinding.ActivityFoodDetailsBinding
import com.jadhavrupesh.techstalwarts.presentation.ui.favourite.FavouriteViewModel
import com.jadhavrupesh.techstalwarts.presentation.ui.home.HomeFragment.Companion.FOOD_DETAILS_ID
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FoodDetailsActivity : AppCompatActivity() {

    private lateinit var viewModel: FoodDetailsViewModel
    private lateinit var binding: ActivityFoodDetailsBinding
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_food_details);
        setContentView(binding.root)
        val data = intent.getIntExtra(FOOD_DETAILS_ID, 0)
        viewModel = ViewModelProvider(this)[FoodDetailsViewModel::class.java]
        viewModel.getFoodDetailsById(data)
        binding.ivBack.setOnClickListener {
            finish()
        }

        viewModel.foodDetails.observe(this) {
            val foodDetails = viewModel.foodDetails.value

            binding.ivHeart.setOnClickListener {
                foodDetails?.let {
                    viewModel.updateFoodDetail(it.copy(isFavourite = !it.isFavourite))
                }
            }

            binding.btnAddToCart.setOnClickListener {
                foodDetails?.let {
                    viewModel.insertCartDetail(it)
                }
            }

            binding.tvTitle.text = "${foodDetails?.name}"
            binding.tvPrice.text = "₹ ${foodDetails?.price}"
            binding.tvDeliveryInfo.text = "${foodDetails?.deliveryInfo}"
            binding.tvReturnPolicy.text = "${foodDetails?.returnPolicy}"

            if (foodDetails?.isFavourite == true) {
                binding.ivHeart.setImageResource(R.drawable.heart)
            } else {
                binding.ivHeart.setImageResource(R.drawable.heart_outline)
            }
            Glide.with(this).load(foodDetails?.imageUrl).into(binding.ivImage)
        }


    }

}