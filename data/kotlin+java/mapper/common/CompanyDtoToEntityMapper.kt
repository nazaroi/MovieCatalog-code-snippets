package com.nazaroi.data.mapper.common

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.common.CompanyEntity
import com.nazaroi.data.remote.dto.common.CompanyDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyDtoToEntityMapper @Inject constructor() :
    DtoToEntityMapper<CompanyDto, CompanyEntity> {
    override fun map(dto: CompanyDto): CompanyEntity {
        return with(dto) {
            CompanyEntity(
                id = id, logo_path = logo_path, name = name, origin_country = origin_country
            )
        }
    }
}