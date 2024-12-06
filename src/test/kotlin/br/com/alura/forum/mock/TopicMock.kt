package br.com.alura.forum.mock

import br.com.alura.forum.model.Topic

object TopicMock {
    fun build() = Topic(
        id = 1,
        title = "topic title",
        message = "message",
        training = TrainingMock.build(),
        user = UserMock.build()
    );
}