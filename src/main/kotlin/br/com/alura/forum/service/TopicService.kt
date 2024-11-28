package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicDto
import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic> = ArrayList(),
    private var trainingService: TrainingService,
    private var userService: UserService
) {

    fun getTopics(): List<Topic> {
        return topics
    }

    fun findById(id: Long): List<Topic> {
        return topics.filter { it.id == id }
    }

    fun create(dto: TopicDto) {
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