package com.soieu.soieuback.controller

import com.soieu.soieuback.dto.BoardRequest
import com.soieu.soieuback.dto.BoardResponse
import com.soieu.soieuback.service.BoardService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/boards")
class BoardController(
    private val boardService: BoardService
) {

    @GetMapping
    fun getBoardsByPage(@RequestParam page: Int): ResponseEntity<List<BoardResponse>> {
        val boardPage = boardService.getBoardsByPage(page - 1)
        val response: List<BoardResponse> = boardPage.content.map { BoardResponse.from(it) }
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createBoard(@Valid @RequestBody request: BoardRequest): ResponseEntity<BoardResponse> {
        val board = boardService.createBoard(request.title, request.content)
        val response = BoardResponse.from(board)
        return ResponseEntity.status(HttpStatus.CREATED).body(response)
    }

    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Long): ResponseEntity<BoardResponse> {
        val board = boardService.getBoard(id)
        val response = BoardResponse.from(board)
        return ResponseEntity.ok(response)
    }


}
