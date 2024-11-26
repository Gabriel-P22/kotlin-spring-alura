package br.com.alura.forum.service

import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.Training
import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: List<Topic>
) {

    init {
        topics = listOf(Topic(
            id = 1,
            title = "",
            user = User(
                id = 1,
                name = "USER_ADMIN",
                email = "email@admin.com"
            ),
            status = StatusTopic.NOT_ANSWERED,
            training = Training(1, "erro backend", "backend"),
            message = "Error na criação da lista"
        ),
            Topic(
                id = 3,
                title = "",
                user = User(
                    id = 1,
                    name = "USER_ADMIN",
                    email = "email3@admin.com"
                ),
                status = StatusTopic.NOT_ANSWERED,
                training = Training(1, "erro backend", "backend"),
                message = "Error na criação da lista"
            ))
    }

    fun getTopics(): List<Topic> {
        return topics
    }

    fun findById(id: Long): List<Topic> {
        return topics.filter { it.id == id }
    }

}