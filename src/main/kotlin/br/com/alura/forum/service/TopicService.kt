package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import br.com.alura.forum.repository.TopicRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class TopicService(
    private val repository: TopicRepository,
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val topicNotFound: String = "Topic not found"
) {

    fun getTopics(
        trainingName: String?,
        pageable: Pageable
    ): Page<TopicView> {
        val topics = if (trainingName == null) {
            repository.findAll(pageable);
        } else {
            repository.findByTrainingName(trainingName, pageable)
        }

        return topics.map { topicViewMapper.map(it) }
    }

    fun findById(id: Long): TopicView {
        return topicViewMapper.map(findTopicById(id));
    }

    fun create(dto: TopicForm): TopicView {
        val topic =  topicFormMapper.map(dto);
        repository.save(topic)
        return topicViewMapper.map(topic);
    }

    fun update(dto: UpdateTopicForm): TopicView {
        val topic = findTopicById(dto.id);
        topic.title = dto.title;
        topic.message = dto.message;
        topic.updatedAt = LocalDateTime.now();
        repository.save(topic);
        return topicViewMapper.map(topic);
    }

    fun delete(id: Long) {
        val topic = findTopicById(id);
        repository.delete(topic);
    }

    fun getReport(): List<TopicPerCategory> {
        return repository.report();
    }

    private fun findTopicById(id: Long): Topic {
        return repository
            .findById(id)
            .orElseThrow{NotFoundException(topicNotFound)}
    }
}