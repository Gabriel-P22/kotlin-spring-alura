package br.com.alura.forum.repository

import br.com.alura.forum.model.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository: JpaRepository<Answer, Long>