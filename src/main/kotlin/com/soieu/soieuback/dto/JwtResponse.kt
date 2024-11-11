package com.soieu.soieuback.dto

data class JwtResponse (
    val jwt: String? = null
) {
    companion object {
        fun from(jwt: String): JwtResponse {
            return JwtResponse(jwt = jwt)
        }
    }
}