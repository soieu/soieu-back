//package com.soieu.soieuback.auth
//
//import com.soieu.soieuback.entity.Member
//import org.springframework.security.core.GrantedAuthority
//import org.springframework.security.core.authority.SimpleGrantedAuthority
//import org.springframework.security.core.userdetails.UserDetails
//
//class MemberDetail(
//    private val member: Member
//) : UserDetails {
//
//    override fun getUsername(): String = member.getMemberId()
//
//    override fun getPassword(): String = member.getPassword()
//
//    override fun isAccountNonExpired(): Boolean = true
//
//    override fun isAccountNonLocked(): Boolean = true
//
//    override fun isCredentialsNonExpired(): Boolean = true
//
//    override fun isEnabled(): Boolean = true
//
//    override fun getAuthorities(): Collection<GrantedAuthority> =
//        listOf(SimpleGrantedAuthority(member.getAuthority()))
//}
