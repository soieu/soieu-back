package com.soieu.soieuback.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


@Component
class JwtUtil {
    @Value("\${jwt.secret}")
    private lateinit var SECRET_KEY: String
    private val EXPIRATION_TIME = 1000 * 60 * 60 * 24

    private fun getSecretKey(): SecretKey {
        val decodedKey = Base64.getDecoder().decode(SECRET_KEY)
        return SecretKeySpec(decodedKey, 0, decodedKey.size, "HmacSHA256")
    }

    fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + EXPIRATION_TIME)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(getSecretKey(), SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder()
                .setSigningKey(getSecretKey())
                .build()
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }

    fun getUsernameFromToken(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(getSecretKey())
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }
}