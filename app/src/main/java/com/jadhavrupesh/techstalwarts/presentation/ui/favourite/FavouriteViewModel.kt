package com.jadhavrupesh.techstalwarts.presentation.ui.favourite

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
class FavouriteViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _foodListLiveData: MutableLiveData<List<FoodDetailsEntity>> = MutableLiveData()
    val foodListLiveData: LiveData<List<FoodDetailsEntity>>
        get() = _foodListLiveData

    init {
        getFevItem()
    }

     fun getFevItem() {
        viewModelScope.launch {
            val items = repository.getAllFoodDetails()
            _foodListLiveData.postValue(items.filter {
                it.isFavourite
            })
        }
    }


}