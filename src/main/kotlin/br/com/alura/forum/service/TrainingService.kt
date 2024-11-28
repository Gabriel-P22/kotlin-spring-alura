package br.com.alura.forum.service


import br.com.alura.forum.model.Training
import org.springframework.stereotype.Service

@Service
class TrainingService(
    private var trainings: List<Training>
) {

    init {
        val training = Training(
            name = "back-end training",
            id = 1,
            category = "Back-end"
        );

        trainings = listOf(training);
    }

    fun findTrainingById(id: Long): Training {
        return trainings.first { it.id == id }
    }

}