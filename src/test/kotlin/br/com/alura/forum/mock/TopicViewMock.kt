package br.com.alura.forum.mock

import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.enum.StatusTopic
import java.time.LocalDateTime

object TopicViewMock {
    fun build() = TopicView(
        id = 1,
        title = "topic title",
        message = "message",
        status = StatusTopic.NOT_ANSWERED,
        createdAt = LocalDateTime.now(),
        updatedAt= LocalDateTime.now()
    );
}