package com.example.lokastay.data.entity

import androidx.room.Entity

@Entity(
    tableName = "favorites",
    primaryKeys = ["userId", "villaId"]
)
data class Favorite(

    val userId: Int,

    val villaId: Int
)