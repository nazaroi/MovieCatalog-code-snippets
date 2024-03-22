package com.nazaroi.data.mapper.review

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.review.AuthorEntity
import com.nazaroi.data.remote.dto.review.AuthorDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorDtoToEntityMapper @Inject constructor() : DtoToEntityMapper<AuthorDto, AuthorEntity> {
    override fun map(dto: AuthorDto): AuthorEntity {
        return with(dto) {
            AuthorEntity(
                avatar_path = avatar_path, username = username, rating = rating, name = name
            )
        }
    }
}