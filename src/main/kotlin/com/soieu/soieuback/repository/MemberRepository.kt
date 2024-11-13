//package com.soieu.soieuback.repository
//
//import com.soieu.soieuback.entity.Member
//import org.springframework.data.jpa.repository.JpaRepository
//
//interface MemberRepository : JpaRepository<Member, Long> {
//    fun findByMemberId(memberId: String): Member?
//    fun existsByMemberId(memberId: String) : Boolean
//}