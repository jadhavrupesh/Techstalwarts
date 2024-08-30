package com.jadhavrupesh.techstalwarts.presentation.ui.home

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity
import com.jadhavrupesh.techstalwarts.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel(), LifecycleObserver {

//    private val _foodList = mutableListOf<FoodDetailsEntity>()
//    val foodList = _foodList

    private val _foodListLiveData: MutableLiveData<List<FoodDetailsEntity>> = MutableLiveData()
    val foodListLiveData: LiveData<List<FoodDetailsEntity>>
        get() = _foodListLiveData

    private val dummyFoodDetailsList = listOf(
        FoodDetailsEntity(
            id = 1,
            name = "Margherita Pizza",
            description = "Tomato sauce, mozzarella, basil",
            price = 150.0,
            imageUrl = "https://www.abeautifulplate.com/wp-content/uploads/2015/08/the-best-homemade-margherita-pizza-1-4.jpg",
            rating = 4.5f,
            deliveryTime = "30-40 min",
            deliveryInfo = "Free delivery on orders above ₹500",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = true,
            isPromo = false
        ), FoodDetailsEntity(
            id = 2,
            name = "Chicken Burger",
            description = "Chicken patty, lettuce, mayo",
            price = 120.0,
            imageUrl = "https://www.recipetineats.com/uploads/2023/09/Crispy-fried-chicken-burgers_5.jpg",
            rating = 4.0f,
            deliveryTime = "20-30 min",
            deliveryInfo = "Free delivery on orders above ₹400",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = false,
            isPromo = true
        ), FoodDetailsEntity(
            id = 3,
            name = "Caesar Salad",
            description = "Romaine lettuce, croutons, Parmesan",
            price = 90.0,
            imageUrl = "https://www.fromvalerieskitchen.com/wordpress/wp-content/uploads/2023/08/Grilled-Chicken-Caesar-Salad-11.jpg",
            rating = 4.3f,
            deliveryTime = "15-20 min",
            deliveryInfo = "Free delivery on orders above ₹300",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = false,
            isPromo = false
        )
    )


    init {
        initializeData()
    }

    private fun initializeData() {
        viewModelScope.launch {
//            repository.deleteAllFoodDetails()
            if (repository.getAllFoodDetails().isEmpty()) {
                addFoods()
                val items = repository.getAllFoodDetails()
                _foodListLiveData.postValue(items)
            } else {
                val items = repository.getAllFoodDetails()
                _foodListLiveData.postValue(items)
            }
        }
    }

    private suspend fun addFoods() {
        dummyFoodDetailsList.forEach { foodDetail ->
            repository.insertFoodDetail(foodDetail)
        }
    }

}