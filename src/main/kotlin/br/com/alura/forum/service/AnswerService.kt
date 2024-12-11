package br.com.alura.forum.service


import br.com.alura.forum.model.Answer
import br.com.alura.forum.model.User
import br.com.alura.forum.repository.AnswerRepository
import br.com.alura.forum.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val repository: AnswerRepository,
    private val emailService: EmailService
) {
    fun create(answer: Answer) {
        repository.save(answer)
        emailService.send(answer.user.name)
    }

}