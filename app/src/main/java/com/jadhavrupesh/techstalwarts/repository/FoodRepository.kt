package com.jadhavrupesh.techstalwarts.repository

import com.jadhavrupesh.techstalwarts.db.CartDao
import com.jadhavrupesh.techstalwarts.db.FoodDetailsDao
import com.jadhavrupesh.techstalwarts.model.CartItemEntity
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity
import javax.inject.Inject


class FoodRepository @Inject constructor(
    private val foodDetailsDao: FoodDetailsDao,
    private val cartDetailsDao: CartDao,
) {

    suspend fun insertFoodDetail(foodDetail: FoodDetailsEntity) {
        foodDetailsDao.insertFoodDetail(foodDetail)
    }

    suspend fun updateFoodDetail(foodDetail: FoodDetailsEntity) {
        foodDetailsDao.updateFoodDetail(foodDetail)
    }

    suspend fun deleteFoodDetail(foodDetail: FoodDetailsEntity) {
        foodDetailsDao.deleteFoodDetail(foodDetail)
    }

    suspend fun getFoodDetailById(id: Int): FoodDetailsEntity? {
        return foodDetailsDao.getFoodDetailById(id)
    }

    suspend fun getAllFoodDetails(): List<FoodDetailsEntity> {
        return foodDetailsDao.getAllFoodDetails()
    }

    suspend fun deleteAllFoodDetails() {
        return foodDetailsDao.deleteAllFoodDetails()
    }

    suspend fun addToCart(foodDetails: FoodDetailsEntity) {
        val cartItem = CartItemEntity(
            foodId = foodDetails.id,
            name = foodDetails.name ?: "",
            price = foodDetails.price,
            imageUrl = foodDetails.imageUrl,
            quantity = 1
        )
        val result = cartDetailsDao.getItemById(foodDetails.id)
        if (result.isEmpty()) {
            cartDetailsDao.insertCartItem(cartItem)
        }
    }

    suspend fun getCartItems(): List<CartItemEntity> {
        return cartDetailsDao.getAllCartItems()
    }

    suspend fun updateCartItem(cartItem: CartItemEntity) {
        return cartDetailsDao.updateCartItem(cartItem)
    }


    suspend fun deleteCartItem(cartItem: CartItemEntity) {
        return cartDetailsDao.deleteCartItem(cartItem)
    }


    suspend fun deleteAllCartItems() {
        return cartDetailsDao.deleteAllCartItems()
    }


}