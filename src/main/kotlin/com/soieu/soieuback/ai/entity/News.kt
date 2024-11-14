package com.soieu.soieuback.ai.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "news")
class News(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(name = "news_summation", nullable = false, length = 4000)
    var newsSummation: String,

    @Column(nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
)


