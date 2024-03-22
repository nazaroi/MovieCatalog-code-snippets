package com.nazaroi.data.local.dao.common

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.common.LanguageEntity

@Dao
interface LanguageDao : BaseDao<LanguageEntity> {

    @Query("SELECT * FROM languages")
    suspend fun getAll(): List<LanguageEntity>

    @Query("SELECT * FROM languages WHERE code = :code")
    suspend fun getByCode(code: String): LanguageEntity?

    @Query("SELECT * FROM languages WHERE code IN (:code)")
    suspend fun getByCodes(code: List<String>): List<LanguageEntity>
}