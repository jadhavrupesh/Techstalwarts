package com.jadhavrupesh.techstalwarts.presentation.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jadhavrupesh.techstalwarts.model.CartItemEntity
import com.jadhavrupesh.techstalwarts.repository.FoodRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repository: FoodRepository
) : ViewModel() {

    private val _cartListLiveData: MutableLiveData<List<CartItemEntity>> = MutableLiveData()
    val cartListLiveData: LiveData<List<CartItemEntity>>
        get() = _cartListLiveData

    init {
        getCartItem()
    }

    fun getCartItem() {
        viewModelScope.launch {
            val items = repository.getCartItems()
            _cartListLiveData.postValue(items)
        }
    }

    fun updateCount(item: CartItemEntity) {
        if (item.quantity == 0) {
            viewModelScope.launch {
                repository.deleteCartItem(item)
            }
        } else {
            viewModelScope.launch {
                repository.updateCartItem(item)
            }
        }
        getCartItem()
    }

    fun clearCart() {
        viewModelScope.launch {
            repository.deleteAllCartItems()
            getCartItem()
        }
    }


}