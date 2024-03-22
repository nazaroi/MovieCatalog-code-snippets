package com.nazaroi.data.local.dao.common

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.common.CompanyEntity

@Dao
interface CompanyDao : BaseDao<CompanyEntity> {

    @Query("SELECT * FROM companies")
    suspend fun getAll(): List<CompanyEntity>

    @Query("SELECT * FROM companies WHERE id = :id")
    suspend fun getById(id: Int): CompanyEntity?

    @Query("SELECT * FROM companies WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Int>): List<CompanyEntity>
}