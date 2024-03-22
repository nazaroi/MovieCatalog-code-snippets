package com.nazaroi.data.local.dao.review

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.review.ReviewEntity

@Dao
interface ReviewDao : BaseDao<ReviewEntity> {

    @Query("SELECT * FROM reviews")
    suspend fun getAll(): List<ReviewEntity>

    @Query("SELECT * FROM reviews WHERE id = :id")
    suspend fun getById(id: String): ReviewEntity?
}