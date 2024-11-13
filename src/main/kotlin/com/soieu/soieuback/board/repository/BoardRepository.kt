package com.soieu.soieuback.board.repository

import com.soieu.soieuback.board.entity.Board
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface BoardRepository : PagingAndSortingRepository<Board, Long>, CrudRepository<Board, Long>
