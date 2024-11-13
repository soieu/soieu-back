package com.soieu.soieuback.board.dto

import com.soieu.soieuback.board.entity.Board
import java.time.LocalDateTime

data class BoardResponse(
    val id: Long,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
) {
    companion object {
        fun from(board: Board) = BoardResponse(
            id = board.id,
            title = board.title,
            content = board.content,
            createdAt = board.createdAt,
            updatedAt = board.updatedAt
        )
    }
}
