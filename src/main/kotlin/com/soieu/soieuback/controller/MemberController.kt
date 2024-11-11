package com.soieu.soieuback.controller

import com.soieu.soieuback.dto.JwtRequest
import com.soieu.soieuback.dto.JwtResponse
import com.soieu.soieuback.service.MemberService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/permit/api/member")
class MemberController (
    private val memberService: MemberService
){
    @PostMapping("/signup")
    fun signup(@RequestBody request: JwtRequest): JwtResponse {

        val jwtToken = memberService.registerMember(request)

        return if (jwtToken != null) {
            JwtResponse(jwt = jwtToken)
        } else {
            JwtResponse(jwt = null)
        }
    }
}