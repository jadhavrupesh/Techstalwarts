package com.jadhavrupesh.techstalwarts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity

@Database(
    entities = [FoodDetailsEntity::class], version = 1
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun foodDetailsDao(): FoodDetailsDao

    companion object {

        const val DATABASE_NAME = "FOOD_DATABASE"

    }
}
