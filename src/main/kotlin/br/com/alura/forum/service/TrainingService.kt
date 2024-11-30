package br.com.alura.forum.service


import br.com.alura.forum.model.Training
import br.com.alura.forum.repository.TrainingRepository
import org.springframework.stereotype.Service

@Service
class TrainingService(
    private val repository: TrainingRepository
) {

    fun findTrainingById(id: Long): Training {
        return repository.getOne(id);
    }

}