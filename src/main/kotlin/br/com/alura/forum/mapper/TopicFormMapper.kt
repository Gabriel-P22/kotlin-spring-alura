package br.com.alura.forum.mapper

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.model.Topic
import br.com.alura.forum.service.TrainingService
import br.com.alura.forum.service.UserService
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TopicFormMapper(
    private val trainingService: TrainingService,
    private val userService: UserService,
): Mapper<TopicForm, Topic> {

    override fun map(t: TopicForm): Topic {
        return Topic(
            message = t.message,
            training = trainingService.findTrainingById(t.idCourse),
            user = userService.findUserById(t.idUser),
            status = StatusTopic.NOT_ANSWERED,
            title = t.title
        )
    }
}


