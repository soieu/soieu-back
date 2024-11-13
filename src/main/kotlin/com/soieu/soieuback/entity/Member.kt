//package com.soieu.soieuback.entity
//
//import jakarta.persistence.*
//import java.time.LocalDateTime
//
//@Entity
//class Member(
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private val id: Long = 0,
//
//    @Column(nullable = false, unique = true)
//    private var memberId: String,
//
//    @Column(nullable = false)
//    private var password: String,
//
//    @Column(nullable = true)
//    private var authority: String,
//
//    @Column(name = "created_at", updatable = false)
//    private val createdAt: LocalDateTime = LocalDateTime.now(),
//
//    @Column(name = "updated_at")
//    private var updatedAt: LocalDateTime = LocalDateTime.now()
//) {
//
//    // Getter methods
//    fun getMemberId(): String = memberId
//    fun getPassword(): String = password
//    fun getAuthority(): String = authority
//
//    companion object {
//        fun of(memberId: String, password: String, authority:String): Member {
//            return Member(memberId = memberId, password = password, authority=authority)
//        }
//    }
//
//    fun update(memberId: String, password: String) {
//        this.memberId = memberId
//        this.password = password
//        this.updatedAt = LocalDateTime.now()
//    }
//}
