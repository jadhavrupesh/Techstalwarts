package com.jadhavrupesh.techstalwarts.presentation.ui.foodDetails

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
class FoodDetailsViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel(), LifecycleObserver {


    private var _foodDetails = MutableLiveData<FoodDetailsEntity>()
    val foodDetails: LiveData<FoodDetailsEntity> = _foodDetails

    fun setFoodDetails(foodDetails: FoodDetailsEntity) {
        viewModelScope.launch {
            val response = repository.getFoodDetailById(foodDetails.id)
            response?.let {
                _foodDetails.value = it
            }
        }
    }

    fun insertCartDetail(foodDetail: FoodDetailsEntity) {
        viewModelScope.launch {
            repository.addToCart(foodDetail)
        }
    }

    fun getFoodDetailsById(id: Int) {
        viewModelScope.launch {
            val response = repository.getFoodDetailById(id)
            response?.let {
                _foodDetails.value = it
            }
        }
    }

    fun updateFoodDetail(foodDetail: FoodDetailsEntity) {
        viewModelScope.launch {
            repository.updateFoodDetail(foodDetail)
            val response = repository.getFoodDetailById(foodDetail.id)
            response?.let {
                _foodDetails.value = it
            }

        }
    }

}