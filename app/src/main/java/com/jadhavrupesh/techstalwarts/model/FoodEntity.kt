package com.jadhavrupesh.techstalwarts.model

import android.os.Parcelable
import androidx.annotation.Keep
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.jadhavrupesh.techstalwarts.utils.ImageUrlsConverter
import kotlinx.android.parcel.Parcelize


@Keep
@Parcelize
@Entity(tableName = "food")
@TypeConverters(ImageUrlsConverter::class)
data class FoodDetailsEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String? = "",
    val description: String,
    val price: Double,
    val imageUrl: String,
    val imageUrls: List<String> = emptyList(),
    val rating: Float,
    val deliveryTime: String,
    val deliveryInfo: String,
    val returnPolicy: String,
    val isFavourite: Boolean = false,
    val isBestSeller: Boolean = false,
    val isPromo: Boolean = false
) : Parcelable