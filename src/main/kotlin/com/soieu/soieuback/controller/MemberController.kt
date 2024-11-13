//package com.soieu.soieuback.controller
//
//import com.soieu.soieuback.dto.JwtRequest
//import com.soieu.soieuback.dto.JwtResponse
//import com.soieu.soieuback.service.MemberService
//import org.springframework.http.HttpStatus
//import org.springframework.http.ResponseEntity
//import org.springframework.web.bind.annotation.PostMapping
//import org.springframework.web.bind.annotation.RequestBody
//import org.springframework.web.bind.annotation.RequestMapping
//import org.springframework.web.bind.annotation.RestController
//
//@RestController
//@RequestMapping("/permit/api/member")
//class MemberController (
//    private val memberService: MemberService
//){
//    @PostMapping("/signup")
//    fun signup(@RequestBody request: JwtRequest): ResponseEntity<JwtResponse> {
//
//        val jwtToken = memberService.registerMember(request)
//
//        return if (jwtToken != null) {
//            return ResponseEntity.ok(JwtResponse(jwt = jwtToken))
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JwtResponse(jwt = null))
//        }
//    }
//
//    @PostMapping("/login")
//    fun logIn(@RequestBody request: JwtRequest): ResponseEntity<JwtResponse> {
//        val jwtToken = memberService.login(request)
//
//        return if (jwtToken != null) {
//            ResponseEntity.ok(JwtResponse(jwtToken)) // 로그인 성공 시 JWT 반환
//        } else {
//            ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(JwtResponse(jwt = null)) // 로그인 실패 시 401 응답
//        }
//    }
//
//}