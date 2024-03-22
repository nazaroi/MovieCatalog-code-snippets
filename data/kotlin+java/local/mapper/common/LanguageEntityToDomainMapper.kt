package com.nazaroi.data.local.mapper.common

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.common.LanguageEntity
import com.nazaroi.domain.model.common.Language
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LanguageEntityToDomainMapper @Inject constructor() :
    EntityToDomainMapper<LanguageEntity, Language> {
    override fun map(entity: LanguageEntity): Language {
        return with(entity) {
            Language(
                englishName = english_name, code = code, name = name
            )
        }
    }
}