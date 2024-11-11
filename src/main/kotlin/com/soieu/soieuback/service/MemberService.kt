package com.soieu.soieuback.service

import com.soieu.soieuback.dto.JwtRequest
import com.soieu.soieuback.entity.Member
import com.soieu.soieuback.repository.MemberRepository
import com.soieu.soieuback.util.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class MemberService (
    private val memberRepository: MemberRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtUtil: JwtUtil
) {
    fun registerMember(request : JwtRequest): String? {
        if (request.isPasswordMismatch) {
            println("비밀번호가 일치하지 않습니다.") // 디버그 로그
            return "unmatch password"
        }
        if (memberRepository.existsByMemberId(request.memberId)) {
            return "already Exist" // TODO 문자 보내는게 아니라 오류를 보내고싶음
        }

        val encodedPassword = passwordEncoder.encode(request.password)
        val member = Member(memberId = request.memberId, password = encodedPassword)
        memberRepository.save(member)

        val token = jwtUtil.generateToken(request.memberId)
        println("JWT 생성 성공: $token") // 디버그 로그
        return token
    }
}
