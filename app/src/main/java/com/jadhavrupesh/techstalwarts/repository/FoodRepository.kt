package com.jadhavrupesh.techstalwarts.repository

import com.jadhavrupesh.techstalwarts.db.FoodDetailsDao
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity
import javax.inject.Inject

class FoodRepository constructor(
    private val foodDetailsDao: FoodDetailsDao
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
}