package com.soieu.soieuback.ai.service

import com.soieu.soieuback.ai.entity.News
import com.soieu.soieuback.ai.repository.NewsRepository
import org.springframework.stereotype.Service

@Service
class NewsService (
    private val newsRepository: NewsRepository
){
    fun getLatestNewsSummation() : News {
        return newsRepository.findTopByOrderByCreatedAtDesc()
    }
}