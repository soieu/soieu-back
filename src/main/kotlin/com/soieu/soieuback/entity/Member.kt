package com.soieu.soieuback.entity

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
data class Member(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false, unique = true)
    var memberId: String,

    @Column(nullable = false)
    private var password: String,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
) : UserDetails {

    override fun getUsername(): String = memberId

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true

    override fun getAuthorities(): Collection<GrantedAuthority> = emptyList() // 권한이 없는 경우 빈 리스트 반환

    companion object {
        fun of(memberId: String, password: String): Member {
            return Member(memberId = memberId, password = password)
        }
    }

    fun update(memberId: String, password: String) {
        this.memberId = memberId
        this.password = password
        this.updatedAt = LocalDateTime.now()
    }
}
