package br.com.alura.forum.service

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.model.Topic
import org.springframework.stereotype.Service

@Service
class TopicService(
    private var topics: MutableList<Topic> = ArrayList(),
    private val topicViewMapper: TopicViewMapper,
    private val topicFormMapper: TopicFormMapper
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
        val topic = topics.first { it.id == id };
        topics = topics.minus(topic).toMutableList();
    }
}