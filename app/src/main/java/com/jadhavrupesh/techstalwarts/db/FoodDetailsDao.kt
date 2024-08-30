package com.jadhavrupesh.techstalwarts.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity

@Dao
interface FoodDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodDetail(foodDetail: FoodDetailsEntity)

    @Update
    suspend fun updateFoodDetail(foodDetail: FoodDetailsEntity)

    @Delete
    suspend fun deleteFoodDetail(foodDetail: FoodDetailsEntity)

    @Query("SELECT * FROM food WHERE id = :id")
    suspend fun getFoodDetailById(id: Int): FoodDetailsEntity?

    @Query("SELECT * FROM food")
    suspend fun getAllFoodDetails(): List<FoodDetailsEntity>

    @Query("DELETE FROM food")
    suspend fun deleteAllFoodDetails()
}