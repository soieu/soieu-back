package com.soieu.soieuback.service

import com.soieu.soieuback.entity.Board
import com.soieu.soieuback.repository.BoardRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BoardService(
    private val boardRepository: BoardRepository
) {
    fun getBoardsByPage(page: Int) : Page<Board> {

        val pageWithElements : Pageable = PageRequest.of(page, 5, Sort.by("createdAt").descending())
        return boardRepository.findAll(pageWithElements)
    }
}
