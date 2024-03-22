package com.nazaroi.data.mapper.common

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.common.CountryEntity
import com.nazaroi.data.remote.dto.common.CountryDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountryDtoToEntityMapper @Inject constructor() : DtoToEntityMapper<CountryDto, CountryEntity> {
    override fun map(dto: CountryDto): CountryEntity {
        return with(dto) {
            CountryEntity(code = iso_3166_1, name = name)
        }
    }
}