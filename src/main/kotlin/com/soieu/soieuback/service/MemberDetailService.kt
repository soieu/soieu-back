//package com.soieu.soieuback.service
//
//import com.soieu.soieuback.auth.MemberDetail
//import com.soieu.soieuback.repository.MemberRepository
//import org.springframework.security.core.userdetails.UserDetails
//import org.springframework.security.core.userdetails.UserDetailsService
//import org.springframework.security.core.userdetails.UsernameNotFoundException
//import org.springframework.stereotype.Service
//
//@Service
//class MemberDetailsService(
//    private val memberRepository: MemberRepository
//) : UserDetailsService {
//
//    override fun loadUserByUsername(username: String): UserDetails {
//        val member = memberRepository.findByMemberId(username)
//            ?: throw UsernameNotFoundException("User not found with username: $username")
//        return MemberDetail(member)
//    }
//}