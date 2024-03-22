package com.nazaroi.data.local.mapper.common

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.common.CountryEntity
import com.nazaroi.domain.model.common.Country
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryEntityToDomainMapper @Inject constructor() :
    EntityToDomainMapper<CountryEntity, Country> {
    override fun map(entity: CountryEntity): Country {
        return with(entity) {
            Country(code = code, name = name)
        }
    }
}