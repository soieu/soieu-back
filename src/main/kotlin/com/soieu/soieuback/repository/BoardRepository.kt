package com.soieu.soieuback.repository

import com.soieu.soieuback.entity.Board
import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository


@Repository
interface BoardRepository : PagingAndSortingRepository<Board, Long>, CrudRepository<Board, Long>
