package br.com.alura.forum.services

import br.com.alura.forum.exception.NotFoundException
import br.com.alura.forum.mapper.TopicFormMapper
import br.com.alura.forum.mapper.TopicViewMapper
import br.com.alura.forum.mock.TopicMock
import br.com.alura.forum.mock.TopicViewMock
import br.com.alura.forum.repository.TopicRepository
import br.com.alura.forum.service.TopicService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Retry.Topic
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.util.*

class TopicServiceTest {

    private val topicPageImplMock = PageImpl(listOf(TopicMock.build()))

    private val pagination: Pageable = mockk()

    private val repository: TopicRepository = mockk {
        every { findByTrainingName(any(), any()) } returns topicPageImplMock;
        every { findAll(pagination) } returns topicPageImplMock;
    };

    private val topicViewMapper: TopicViewMapper = mockk {
        every { map(any()) } returns TopicViewMock.build()
    };
    private val topicFormMapper: TopicFormMapper = mockk();

    private val topicTopicService = TopicService(
        repository,
        topicViewMapper,
        topicFormMapper
    );

    @Test
    fun `do list topic with topic name`() {
        topicTopicService.getTopics("Kotlin Training", pagination)

        verify(exactly = 1) { repository.findByTrainingName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
    }

    @Test
    fun `do list topic with name null`() {
        topicTopicService.getTopics(null, pagination)

        verify(exactly = 0) { repository.findByTrainingName(any(), any()) }
        verify(exactly = 1) { topicViewMapper.map(any()) }
        verify(exactly = 1) { repository.findAll(pagination) }
    }

    @Test
    fun `when call service with a invalid id then return exception`() {
        every { repository.findById(any()) } returns Optional.empty();

        val response = assertThrows<NotFoundException> {
            topicTopicService.findById(1)
        }

        assertThat(response.message).isEqualTo("Topic not found")
    }

}