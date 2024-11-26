package br.com.alura.forum.model

import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime

data class Topic(
    val id: Long? = null,
    val title: String,
    val message: String,
    val createdAt: LocalDateTime = LocalDateTime.now(),
    val training: Training,
    val user: User,
    val status: StatusTopic = StatusTopic.NOT_ANSWERED,
    val answers: List<Answer> = ArrayList()
) {

}