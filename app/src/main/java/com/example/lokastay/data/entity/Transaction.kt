package com.example.lokastay.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class Transaction(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val userId: Int,

    val villaId: Int,

    val nights: Int,

    val totalPrice: Double,

    val paymentStatus: String
)