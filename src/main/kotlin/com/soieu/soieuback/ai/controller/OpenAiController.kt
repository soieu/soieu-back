package com.soieu.soieuback.ai.controller

import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
@RequestMapping("/ai")
class ChatController @Autowired constructor(private val chatModel: OpenAiChatModel) {
    @GetMapping("/generateStream")
    fun generateStream(
        @RequestParam(
            value = "message",
            defaultValue = "Tell me a joke"
        ) message: String?
    ): Flux<ChatResponse> {
        val prompt = Prompt(UserMessage(message))
        return chatModel.stream(prompt)
    }
}