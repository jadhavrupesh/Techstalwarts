package com.jadhavrupesh.techstalwarts.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Keep
@Parcelize
@Entity(tableName = "cart")
data class CartItemEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val foodId: Int,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int
) : Parcelable