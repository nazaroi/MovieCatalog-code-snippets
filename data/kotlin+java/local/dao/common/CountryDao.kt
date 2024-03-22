package com.nazaroi.data.local.dao.common

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.common.CountryEntity

@Dao
interface CountryDao : BaseDao<CountryEntity> {

    @Query("SELECT * FROM countries")
    suspend fun getAll(): List<CountryEntity>

    @Query("SELECT * FROM countries WHERE code = :code")
    suspend fun getByCode(code: String): CountryEntity?

    @Query("SELECT * FROM countries WHERE code IN (:codes)")
    suspend fun getByCodes(codes: List<String>): List<CountryEntity>
}