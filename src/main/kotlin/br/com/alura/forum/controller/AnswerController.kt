package br.com.alura.forum.controller


import br.com.alura.forum.model.Answer
import br.com.alura.forum.service.AnswerService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
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
        @RequestBody @Valid answer: Answer,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<Answer> {
        val topicView = service.create(answer)

        val uri = uriBuilder
            .path("/topic/${answer.id}")
            .build()
            .toUri();

        return ResponseEntity.created(uri).body(answer)
    }
}