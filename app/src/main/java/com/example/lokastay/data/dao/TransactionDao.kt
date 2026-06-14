package com.example.lokastay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lokastay.data.entity.Transaction

@Dao
interface TransactionDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(transaction: Transaction)

    @Query("""
        SELECT * FROM transactions
        WHERE userId = :userId
    """)
    suspend fun getUserTransactions(
        userId: Int
    ): List<Transaction>

    @Query("SELECT COUNT(*) FROM transactions")
    suspend fun countTransactions(): Int
}