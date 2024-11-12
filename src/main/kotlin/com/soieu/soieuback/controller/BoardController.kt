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
        return try {
            val boardPage = boardService.getBoardsByPage(page - 1)
            val response = boardPage.content.map { BoardResponse.from(it) }
            ResponseEntity.ok(response)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(emptyList())
        }
    }

    @PostMapping
    fun createBoard(@Valid @RequestBody request: BoardRequest): ResponseEntity<BoardResponse> {
        return try {
            val board = boardService.createBoard(request.title, request.content)
            val response = BoardResponse.from(board)
            ResponseEntity.status(HttpStatus.CREATED).body(response)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
        }
    }

    @GetMapping("/{id}")
    fun getBoard(@PathVariable id: Long): ResponseEntity<BoardResponse> {
        return try {
            val board = boardService.getBoard(id)
            val response = BoardResponse.from(board)
            ResponseEntity.ok(response)
        } catch (e: Exception) {
            ResponseEntity.status(HttpStatus.NOT_FOUND).build()
        }
    }
}
