package com.soieu.soieuback.filter

import com.soieu.soieuback.util.JwtUtil
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter(
    private val jwtUtil: JwtUtil,
    private val userDetailsService: UserDetailsService
) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        // Authorization 헤더에서 JWT 토큰 추출
        val authHeader = request.getHeader("Authorization")
        val token = authHeader?.takeIf { it.startsWith("Bearer ") }?.substring(7)

        // 토큰이 유효하고 SecurityContext에 인증이 없을 때만 처리
        if (token != null && SecurityContextHolder.getContext().authentication == null) {
            // JWT 유효성 검사
            if (jwtUtil.validateToken(token)) {
                // 토큰에서 사용자 이름 추출
                val username = jwtUtil.getUsernameFromToken(token)
                val userDetails = userDetailsService.loadUserByUsername(username)

                // 인증 객체 생성 및 SecurityContext에 설정
                val authToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                SecurityContextHolder.getContext().authentication = authToken
            }
        }

        // 다음 필터로 요청 전달
        chain.doFilter(request, response)
    }
}
