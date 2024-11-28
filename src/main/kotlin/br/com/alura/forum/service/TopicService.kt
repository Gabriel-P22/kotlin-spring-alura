package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic> = ArrayList(),
    private var trainingService: TrainingService,
    private var userService: UserService,
    private val mapper: TopicViewMapper
) {

    fun getTopics(): List<TopicView> {
        return topics.map { mapper.map(it) }
    }

    fun findById(id: Long): List<TopicView> {
        return topics
                .filter { it.id == id }
                .map { mapper.map(it)}
    }

    fun create(dto: TopicForm) {
        topics.add(
            Topic(
                id = topics.size.toLong() + 1,
                message = dto.message,
                training = trainingService.findTrainingById(dto.idCourse),
                user = userService.findUserById(dto.idUser),
                status = StatusTopic.NOT_ANSWERED,
                title = dto.title
            )
        )
    }
}