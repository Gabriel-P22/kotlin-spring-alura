package br.com.alura.forum.controller

import br.com.alura.forum.enum.StatusTopic
import br.com.alura.forum.model.Answer
import br.com.alura.forum.model.Topic
import br.com.alura.forum.model.Training
import br.com.alura.forum.model.User
import br.com.alura.forum.service.TopicService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/topic")
class TopicController(
    private val service: TopicService
) {

    @GetMapping
    fun getTopics(): List<Topic> {
        return service.getTopics();
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): List<Topic> {
        return service.findById(id);
    }

}