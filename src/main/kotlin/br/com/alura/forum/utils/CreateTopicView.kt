package br.com.alura.forum.utils

import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.model.Topic

class CreateTopicView {
    fun create(topic: Topic): TopicView {
        return TopicView(
            message = topic.message,
            id = topic.id,
            title = topic.title,
            status = topic.status,
            createdAt = topic.createdAt
        );
    }
}