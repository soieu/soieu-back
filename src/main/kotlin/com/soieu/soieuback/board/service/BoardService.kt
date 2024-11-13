package com.soieu.soieuback.board.service

import com.soieu.soieuback.board.entity.Board
import com.soieu.soieuback.board.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    @Transactional
    fun getBoardsByPage(page: Int) : Page<Board> {
        val pageWithElements : Pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending())
        return boardRepository.findAll(pageWithElements)
    }
    @Transactional
    fun createBoard(title: String, content: String) : Board {
        val Board = Board.of(title, content)
        return boardRepository.save(Board)
    }
    @Transactional
    fun getBoard(id: Long): Board {
        return boardRepository.findById(id).orElseThrow {
            NoSuchElementException("Board with ID $id not found")
        }
    }
}

