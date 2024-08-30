package com.jadhavrupesh.techstalwarts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jadhavrupesh.techstalwarts.model.CartItemEntity
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity

@Database(
    entities = [FoodDetailsEntity::class, CartItemEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDetailsDao(): FoodDetailsDao
    abstract fun cartDao(): CartDao

    companion object {
        const val FOOD_DATABASE_NAME = "FOOD_DATABASE"
    }
}
