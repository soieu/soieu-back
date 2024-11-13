//package com.soieu.soieuback.service
//
//import com.soieu.soieuback.dto.JwtRequest
//import com.soieu.soieuback.entity.Member
//import com.soieu.soieuback.repository.MemberRepository
//import com.soieu.soieuback.util.JwtUtil
//import org.springframework.security.crypto.password.PasswordEncoder
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//
//@Service
//class MemberService (
//    private val memberRepository: MemberRepository,
//    private val passwordEncoder: PasswordEncoder,
//    private val jwtUtil: JwtUtil
//) {
//    @Transactional
//    fun registerMember(request : JwtRequest): String? {
//        if (memberRepository.existsByMemberId(request.memberId)) {
//            return "already Exist" // TODO 문자 보내는게 아니라 오류를 보내고싶음
//        }
//
//        val encodedPassword = passwordEncoder.encode(request.password)
//        val member = Member(memberId = request.memberId, password = encodedPassword, authority = request.authority)
//        memberRepository.save(member)
//
//        val token = jwtUtil.generateToken(request.memberId)
//        println("JWT 생성 성공: $token") // 디버그 로그
//        return token
//    }
//    fun login(request: JwtRequest): String? {
//        val member = memberRepository.findByMemberId(request.memberId)
//
//        // 사용자 인증 확인
//        return if (member != null && passwordEncoder.matches(request.password, member.getPassword())) {
//            jwtUtil.generateToken(member.getMemberId()) // JWT 토큰 생성 후 반환
//        } else {
//            null // 인증 실패 시 null 반환
//        }
//    }
//}
