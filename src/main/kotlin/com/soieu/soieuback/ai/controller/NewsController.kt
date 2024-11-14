package com.soieu.soieuback.ai.controller

import com.soieu.soieuback.ai.entity.News
import com.soieu.soieuback.ai.service.NewsService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class NewsController (
    private val newsService : NewsService
){
    @GetMapping("/latestNews")
    fun getLatestNewsSummation() : News  {
        print("test")
        return newsService.getLatestNewsSummation()
    }
}