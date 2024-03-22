package com.nazaroi.data.local.mapper.credit

import com.nazaroi.base.mapper.EntityToDomainMapper
import com.nazaroi.data.local.entity.credit.CreditMemberEntity
import com.nazaroi.domain.enums.CreditMemberType
import com.nazaroi.domain.model.credit.CreditMember
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CreditMemberEntityToDomainMapper @Inject constructor(
    private val creditMemberEntityToCastMemberMapper: CreditMemberEntityToCastMemberMapper,
    private val creditMemberEntityToCrewMemberMapper: CreditMemberEntityToCrewMemberMapper
) : EntityToDomainMapper<CreditMemberEntity, CreditMember> {
    override fun map(entity: CreditMemberEntity): CreditMember {
        return when (entity.type) {
            CreditMemberType.Cast -> creditMemberEntityToCastMemberMapper.map(entity)
            CreditMemberType.Crew -> creditMemberEntityToCrewMemberMapper.map(entity)
        }
    }
}