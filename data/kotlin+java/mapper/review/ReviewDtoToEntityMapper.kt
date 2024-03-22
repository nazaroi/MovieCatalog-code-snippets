package com.nazaroi.data.mapper.review

import com.nazaroi.base.mapper.DtoToEntityMapper
import com.nazaroi.data.local.entity.review.ReviewEntity
import com.nazaroi.data.remote.dto.review.ReviewDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ReviewDtoToEntityMapper @Inject constructor() : DtoToEntityMapper<ReviewDto, ReviewEntity> {
    override fun map(dto: ReviewDto): ReviewEntity {
        return with(dto) {
            ReviewEntity(
                id = id,
                author_username = author_details.username,
                content = content,
                created_at = created_at,
                updated_at = updated_at,
                url = url
            )
        }
    }
}