package br.com.alura.forum.mock

import br.com.alura.forum.model.Training

object TrainingMock {
    fun build() = Training(
        id = 1,
        name = "Kotlin Training",
        category = "Programing"
    );
}