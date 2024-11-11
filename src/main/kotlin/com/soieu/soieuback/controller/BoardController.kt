package com.soieu.soieuback.controller

import com.soieu.soieuback.entity.Board
import com.soieu.soieuback.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boards")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping
    fun getBoardsByPage(@RequestParam page: Int): Page<Board?> {
        return boardService.getBoardsByPage(page - 1)
    }

    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Long) : Board? {
        return boardService.getBoard(id)
    }

}
