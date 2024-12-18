package com.soieu.soieuback.ai.service

import com.soieu.soieuback.ai.entity.News
import com.soieu.soieuback.ai.repository.NewsRepository
import org.springframework.ai.chat.messages.UserMessage
import org.springframework.ai.chat.model.ChatResponse
import org.springframework.ai.chat.prompt.Prompt
import org.springframework.ai.openai.OpenAiChatModel
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.UriComponentsBuilder
import java.util.*

@Service
class AiService(
    private val chatModel: OpenAiChatModel,
    private val newsRepository: NewsRepository
) {
    fun saveNewsSummation() {
        // Step 1: Fetch latest 20 news articles from NewsAPI
        val latestNews = fetchLatestNews()

        if (latestNews.isNotEmpty()) {
            // Step 2: Combine all news into a single string
            val combinedNewsContent = latestNews.joinToString("\n\n") { it }

            // Step 3: Create a single prompt for the combined news
            val prompt = createPrompt(combinedNewsContent)

            // Step 4: Get a single summary from the chat model
            val summary = getSummaryFromChatModel(prompt)

            // Step 5: Save the single summarized content to the database
            saveNewsToDatabase(summary)
        } else {
            println("No news articles found to summarize.")
        }
    }

    private fun fetchLatestNews(): List<String> {
        val newsApiUrl = "https://newsapi.org/v2/everything"
        val apiKey = "06114161db9545c780d319993c590cd7" // API 키

        val uri = UriComponentsBuilder.fromHttpUrl(newsApiUrl)
            .queryParam("q", "latest") // 검색 키워드
            .queryParam("sortBy", "publishedAt") // 최신순 정렬
            .queryParam("pageSize", 40)
            .queryParam("apiKey", apiKey) // API 키
            .build()
            .toUri()

        val restTemplate = RestTemplate()
        val response = restTemplate.getForEntity(uri, Map::class.java)

        val articles = response.body?.get("articles") as? List<Map<String, Any>> ?: emptyList()

        return articles.mapNotNull {
            it["content"] as? String
        }
    }

    private fun createPrompt(newsContent: String): Prompt {
        val userMessage = UserMessage("한글로 대답해!! 다음 뉴스들을 한글 기준 2000자 이내로 요약해줘 가독성 좋게 개행도 넣고 구분하기 쉽게 구분자도 넣어줘! 강조도 하면서 형식은 html으로!!! ```html```는 빼줘:\n\n$newsContent")
        return Prompt(userMessage)
    }

    private fun getSummaryFromChatModel(prompt: Prompt): String {
        val responseBuilder = StringBuilder()

        try {
            val responseStream = chatModel.stream(prompt)

            // 스트림 데이터 동기 수집
            responseStream.toIterable().forEach { chatResponse: ChatResponse ->
                val content = chatResponse.getResult().getOutput().getContent()
                if (content != null) {
                    responseBuilder.append(content)
                }
            }
        } catch (e: Exception) {
            println("Error while streaming response: ${e.message}")
            return "Error occurred while summarizing."
        }

        val result = responseBuilder.toString().trim()
        return if (result.isBlank()) "No summary available." else result
    }

    private fun saveNewsToDatabase(summary: String) {
        if (summary.isBlank()) {
            return
        }

        val news = News(
            newsSummation = summary
        )

        newsRepository.save(news)
    }
}
