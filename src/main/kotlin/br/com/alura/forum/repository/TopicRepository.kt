package br.com.alura.forum.repository

import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.model.Topic
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface TopicRepository: JpaRepository<Topic, Long> {
    fun findByTrainingName(trainingName: String, pageable: Pageable): Page<Topic>

    @Query("SELECT new br.com.alura.forum.dto.TopicPerCategory(training.category, count(t)) FROM Topic t JOIN t.training training GROUP BY training.category")
    fun report(): List<TopicPerCategory>;
}