package com.nazaroi.data.local.dao.credit

import androidx.room.Dao
import androidx.room.Query
import com.nazaroi.base.dao.BaseDao
import com.nazaroi.data.local.entity.credit.CreditMemberEntity

@Dao
interface CreditMemberDao : BaseDao<CreditMemberEntity> {
    @Query("SELECT * FROM credit_members")
    suspend fun getAll(): List<CreditMemberEntity>

    @Query("SELECT * FROM credit_members WHERE member_id = :memberId")
    suspend fun getByMemberId(memberId: String): CreditMemberEntity?

    @Query(
        """
        SELECT credit_members.* FROM credit_members
        INNER JOIN movie_credit_member_cross_ref ON credit_members.member_id = movie_credit_member_cross_ref.creditMemberId
        WHERE movie_credit_member_cross_ref.movieId = :movieId
    """
    )
    suspend fun getAllByMovieId(movieId: Int): List<CreditMemberEntity>
}