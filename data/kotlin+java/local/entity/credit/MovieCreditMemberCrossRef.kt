package com.nazaroi.data.local.entity.credit

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.nazaroi.data.local.entity.movie.MovieEntity

@Entity(
    tableName = "movie_credit_member_cross_ref",
    primaryKeys = ["movieId", "creditMemberId"],
    foreignKeys = [ForeignKey(
        entity = MovieEntity::class,
        parentColumns = ["id"],
        childColumns = ["movieId"],
        onDelete = ForeignKey.CASCADE
    ), ForeignKey(
        entity = CreditMemberEntity::class,
        parentColumns = ["member_id"],
        childColumns = ["creditMemberId"],
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["movieId"]), Index(value = ["creditMemberId"])]
)
data class MovieCreditMemberCrossRef(
    val movieId: Int, val creditMemberId: String
)