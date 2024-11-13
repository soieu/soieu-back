package com.soieu.soieuback.board.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Board(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var title: String,

    @Column(columnDefinition = "TEXT")
    var content: String,

    @Column(name = "created_at", updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    companion object {
        fun of(title: String, content: String): Board {
            return Board(title = title, content = content)
        }
    }

    fun update(title: String, content: String) {
        this.title = title
        this.content = content
        this.updatedAt = LocalDateTime.now()
    }
}
