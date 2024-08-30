package com.jadhavrupesh.techstalwarts.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.jadhavrupesh.techstalwarts.model.CartItemEntity
import com.jadhavrupesh.techstalwarts.model.FoodDetailsEntity

@Dao
interface CartDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCartItem(cartItem: CartItemEntity)

    @Update
    suspend fun updateCartItem(cartItem: CartItemEntity)

    @Delete
    suspend fun deleteCartItem(cartItem: CartItemEntity)

    @Query("SELECT * FROM cart WHERE foodId = :id")
    suspend fun getItemById(id: Int): List<CartItemEntity>


    @Query("SELECT * FROM cart")
    suspend fun getAllCartItems(): List<CartItemEntity>

    @Query("DELETE FROM cart")
    suspend fun deleteAllCartItems()

}