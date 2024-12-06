package br.com.alura.forum.dto

import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime

data class TopicView(
    val id: Long? = null,
    val title: String,
    val message: String,
    val status: StatusTopic,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime?
    ) {

}