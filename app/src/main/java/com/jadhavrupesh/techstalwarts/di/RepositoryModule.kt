package com.jadhavrupesh.techstalwarts.di

import com.jadhavrupesh.techstalwarts.db.CartDao
import com.jadhavrupesh.techstalwarts.db.FoodDetailsDao
import com.jadhavrupesh.techstalwarts.repository.FoodRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepositoryModule {

    @Singleton
    @Provides
    fun provideFoodRepository(
        foodDetailsDao: FoodDetailsDao,
        cartDao: CartDao
    ): FoodRepository {
        return FoodRepository(foodDetailsDao, cartDao)
    }

}