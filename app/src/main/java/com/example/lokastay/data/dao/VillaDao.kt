package com.example.lokastay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.lokastay.data.entity.Villa

@Dao
interface VillaDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(villas: List<Villa>)

    @Query("SELECT * FROM villas")
    suspend fun getAllVillas(): List<Villa>

    @Query("SELECT * FROM villas WHERE id = :villaId")
    suspend fun getVillaById(villaId: Int): Villa

    @Query("""
        SELECT * FROM villas
        WHERE location LIKE '%' || :location || '%'
        AND rating >= :rating
        AND pricePerNight BETWEEN :minPrice AND :maxPrice
    """)
    suspend fun filterVilla(
        location: String,
        rating: Float,
        minPrice: Double,
        maxPrice: Double
    ): List<Villa>

    @Query("SELECT COUNT(*) FROM villas")
    suspend fun countVillas(): Int
}