package com.soieu.soieuback.ai.repository

import com.soieu.soieuback.ai.entity.News
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface NewsRepository  : JpaRepository<News, UUID> {
    fun findTopByOrderByCreatedAtDesc(): News
}