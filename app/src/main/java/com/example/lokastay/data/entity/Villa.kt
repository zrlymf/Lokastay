package com.example.lokastay.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "villas")
data class Villa(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,

    val location: String,

    val pricePerNight: Double,

    val rating: Float,

    val imageUrl: String,

    val description: String
)