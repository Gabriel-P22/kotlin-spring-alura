package br.com.alura.forum.repository

import br.com.alura.forum.model.Training
import org.springframework.data.jpa.repository.JpaRepository

interface TrainingRepository: JpaRepository<Training, Long> {
}