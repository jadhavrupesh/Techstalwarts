package com.jadhavrupesh.techstalwarts.utils

import androidx.room.TypeConverter

class ImageUrlsConverter {
    @TypeConverter
    fun fromImageUrls(imageUrls: List<String>): String {
        return imageUrls.joinToString(separator = ",") // Convert list to a comma-separated string
    }

    @TypeConverter
    fun toImageUrls(data: String): List<String> {
        return data.split(",") // Convert comma-separated string back to a list
    }
}