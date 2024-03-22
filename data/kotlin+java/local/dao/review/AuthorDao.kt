package com.nazaroi.data.local.dao.review

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.review.AuthorEntity

@Dao
interface AuthorDao : BaseDao<AuthorEntity> {

    @Query("SELECT * FROM authors")
    suspend fun getAll(): List<AuthorEntity>

    @Query("SELECT * FROM authors WHERE username = :username")
    suspend fun getByUsername(username: String): AuthorEntity?
}