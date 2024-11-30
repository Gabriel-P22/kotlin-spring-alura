package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic> = ArrayList(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper,
    private val topicNotFound: String = "Topic not found"
) {

    fun getTopics(): List<TopicView> {
        return topics.map { topicViewMapper.map(it) }
    }

    fun findById(id: Long): List<TopicView> {
        return topics
                .filter { it.id == id }
                .map { topicViewMapper.map(it)}
    }

    fun create(dto: TopicForm): TopicView {
        val topic =  topicFormMapper.map(dto);
        topic.id = topics.size.toLong() + 1
        topics.add(topic);

        return topicViewMapper.map(topic);
    }

    fun update(dto: UpdateTopicForm): TopicView {
        val topic = topics.first { it.id == dto.id };

        topics = topics.minus(topic).toMutableList();

        topic.title = dto.title;
        topic.message = dto.message;

        topics.add(topic);
        return topicViewMapper.map(topic);
    }

    fun delete(id: Long) {
        val topic = findTopicById(id, topics);
        topics = topics.minus(topic).toMutableList();
    }

    private fun findTopicById(id: Long, topicList: List<Topic>): Topic {
        return topicList
            .stream()
            .filter { it.id == id }
            .findFirst()
            .orElseThrow{NotFoundException(topicNotFound)};
    }
}