package br.com.alura.forum.controller

import br.com.alura.forum.dto.TopicForm
import br.com.alura.forum.dto.TopicView
import br.com.alura.forum.service.TopicService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topic")
class TopicController(
    private val service: TopicService
) {

    @GetMapping
    fun getTopics(): List<TopicView> {
        return service.getTopics();
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): List<TopicView> {
        return service.findById(id);
    }

    @PostMapping()
    fun createTopic(@RequestBody @Valid topic: TopicForm) {
        service.create(topic)
    }

}