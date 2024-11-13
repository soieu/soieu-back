package com.soieu.soieuback.ai.scheduler

import com.soieu.soieuback.ai.service.AiService
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import java.util.*


@Component
class NewsScheduler (
    private val aiService : AiService
){
    @Scheduled(initialDelay = 1000, fixedDelay = 3600000)
    fun saveNewsSummation() {
        return aiService.saveNewsSummation()

    }
}