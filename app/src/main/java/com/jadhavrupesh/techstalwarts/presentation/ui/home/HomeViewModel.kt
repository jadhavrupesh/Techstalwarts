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
            description = "Classic Margherita pizza with fresh mozzarella and basil.",
            price = 8.99,
            imageUrl = "https://example.com/margherita_pizza.jpg",
            imageUrls = listOf(
                "https://example.com/margherita_pizza_1.jpg",
                "https://example.com/margherita_pizza_2.jpg"
            ),
            rating = 4.5f,
            deliveryTime = "30-40 min",
            deliveryInfo = "Free delivery on orders above $20",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = true,
            isPromo = false
        ), FoodDetailsEntity(
            id = 2,
            name = "Chicken Burger",
            description = "Juicy chicken burger with lettuce, tomato, and mayo.",
            price = 5.99,
            imageUrl = "https://example.com/chicken_burger.jpg",
            imageUrls = listOf(
                "https://example.com/chicken_burger_1.jpg",
                "https://example.com/chicken_burger_2.jpg"
            ),
            rating = 4.0f,
            deliveryTime = "20-30 min",
            deliveryInfo = "Free delivery on orders above $15",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = false,
            isPromo = true
        ), FoodDetailsEntity(
            id = 3,
            name = "Caesar Salad",
            description = "Fresh Caesar salad with crispy croutons and Parmesan cheese.",
            price = 6.49,
            imageUrl = "https://example.com/caesar_salad.jpg",
            imageUrls = listOf(
                "https://example.com/caesar_salad_1.jpg", "https://example.com/caesar_salad_2.jpg"
            ),
            rating = 4.3f,
            deliveryTime = "15-20 min",
            deliveryInfo = "Free delivery on orders above $10",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = false,
            isPromo = false
        ), FoodDetailsEntity(
            id = 4,
            name = "Spaghetti Carbonara",
            description = "Creamy spaghetti carbonara with pancetta and Parmesan.",
            price = 9.99,
            imageUrl = "https://example.com/spaghetti_carbonara.jpg",
            imageUrls = listOf(
                "https://example.com/spaghetti_carbonara_1.jpg",
                "https://example.com/spaghetti_carbonara_2.jpg"
            ),
            rating = 4.6f,
            deliveryTime = "25-35 min",
            deliveryInfo = "Free delivery on orders above $20",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = true,
            isPromo = true
        ), FoodDetailsEntity(
            id = 5,
            name = "Grilled Chicken",
            description = "Grilled chicken with herbs, served with roasted vegetables.",
            price = 12.49,
            imageUrl = "https://example.com/grilled_chicken.jpg",
            imageUrls = listOf(
                "https://example.com/grilled_chicken_1.jpg",
                "https://example.com/grilled_chicken_2.jpg"
            ),
            rating = 4.8f,
            deliveryTime = "35-45 min",
            deliveryInfo = "Free delivery on orders above $25",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = true,
            isPromo = false
        ), FoodDetailsEntity(
            id = 6,
            name = "Beef Tacos",
            description = "Soft tacos filled with seasoned beef, cheese, and salsa.",
            price = 7.99,
            imageUrl = "https://example.com/beef_tacos.jpg",
            imageUrls = listOf(
                "https://example.com/beef_tacos_1.jpg", "https://example.com/beef_tacos_2.jpg"
            ),
            rating = 4.2f,
            deliveryTime = "20-30 min",
            deliveryInfo = "Free delivery on orders above $15",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = false,
            isPromo = true
        ), FoodDetailsEntity(
            id = 7,
            name = "Vegan Buddha Bowl",
            description = "Healthy vegan Buddha bowl with quinoa, avocado, and chickpeas.",
            price = 10.49,
            imageUrl = "https://example.com/vegan_buddha_bowl.jpg",
            imageUrls = listOf(
                "https://example.com/vegan_buddha_bowl_1.jpg",
                "https://example.com/vegan_buddha_bowl_2.jpg"
            ),
            rating = 4.7f,
            deliveryTime = "25-35 min",
            deliveryInfo = "Free delivery on orders above $20",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = true,
            isPromo = false
        ), FoodDetailsEntity(
            id = 8,
            name = "Fish and Chips",
            description = "Crispy battered fish served with golden fries.",
            price = 11.99,
            imageUrl = "https://example.com/fish_and_chips.jpg",
            imageUrls = listOf(
                "https://example.com/fish_and_chips_1.jpg",
                "https://example.com/fish_and_chips_2.jpg"
            ),
            rating = 4.4f,
            deliveryTime = "30-40 min",
            deliveryInfo = "Free delivery on orders above $20",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = false,
            isPromo = true
        ), FoodDetailsEntity(
            id = 9,
            name = "Chocolate Cake",
            description = "Decadent chocolate cake with layers of rich chocolate ganache.",
            price = 6.99,
            imageUrl = "https://example.com/chocolate_cake.jpg",
            imageUrls = listOf(
                "https://example.com/chocolate_cake_1.jpg",
                "https://example.com/chocolate_cake_2.jpg"
            ),
            rating = 4.9f,
            deliveryTime = "20-30 min",
            deliveryInfo = "Free delivery on orders above $15",
            returnPolicy = "No returns on food items",
            isFavourite = true,
            isBestSeller = true,
            isPromo = false
        ), FoodDetailsEntity(
            id = 10,
            name = "Pasta Alfredo",
            description = "Creamy Alfredo pasta with Parmesan and fresh parsley.",
            price = 8.49,
            imageUrl = "https://example.com/pasta_alfredo.jpg",
            imageUrls = listOf(
                "https://example.com/pasta_alfredo_1.jpg", "https://example.com/pasta_alfredo_2.jpg"
            ),
            rating = 4.3f,
            deliveryTime = "25-35 min",
            deliveryInfo = "Free delivery on orders above $20",
            returnPolicy = "No returns on food items",
            isFavourite = false,
            isBestSeller = true,
            isPromo = true
        )
    )


    init {
        println("HomeViewModel created")
        initializeData()
    }

    private fun initializeData() {
        viewModelScope.launch {
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