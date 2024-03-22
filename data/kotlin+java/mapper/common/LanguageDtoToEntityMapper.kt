package com.nazaroi.data.mapper.common

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.common.LanguageEntity
import com.nazaroi.data.remote.dto.common.LanguageDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageDtoToEntityMapper @Inject constructor() :
    DtoToEntityMapper<LanguageDto, LanguageEntity> {
    override fun map(dto: LanguageDto): LanguageEntity {
        return with(dto) {
            LanguageEntity(
                code = iso_639_1, english_name = english_name, name = name
            )
        }
    }
}