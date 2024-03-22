package com.nazaroi.data.local.mapper.common

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.common.CompanyEntity
import com.nazaroi.domain.model.common.Company
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompanyEntityToDomainMapper @Inject constructor() :
    EntityToDomainMapper<CompanyEntity, Company> {
    override fun map(entity: CompanyEntity): Company {
        return with(entity) {
            Company(
                id = id, logoPath = logo_path, name = name, originCountry = origin_country
            )
        }
    }
}