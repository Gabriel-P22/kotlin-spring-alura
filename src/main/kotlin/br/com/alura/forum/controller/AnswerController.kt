package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.dto.UpdateTopicForm
import br.com.alura.forum.model.Answer
import br.com.alura.forum.service.AnswerService
import br.com.alura.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/answer")
class AnswerController(
    private val service: AnswerService
) {
    @PostMapping()
    @Transactional
    fun createAnswer(
        @RequestBody @Valid topic: Answer,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Answer> {
        val topicView = service.create(topic)

        val uri = uriBuilder
            .path("/topic/${topicView.id}")
            .build()
            .toUri();

        return ResponseEntity.created(uri).body(topicView)
    }
}