package com.jadhavrupesh.techstalwarts.di

import android.content.Context
import androidx.room.Room
import com.jadhavrupesh.techstalwarts.db.AppDatabase
import com.jadhavrupesh.techstalwarts.db.AppDatabase.Companion.DATABASE_NAME
import com.jadhavrupesh.techstalwarts.db.FoodDetailsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun providersRoom(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            DATABASE_NAME,
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun providesPostDao(appDatabase: AppDatabase): FoodDetailsDao {
        return appDatabase.foodDetailsDao()
    }


}