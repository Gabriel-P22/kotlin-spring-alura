package br.com.alura.forum.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class TopicForm(
    @field: NotEmpty @field:Size(min = 5, max = 100) val title: String,
    @field: NotEmpty val message: String,
    @field: NotNull val idCourse: Long,
    @field: NotNull val idUser: Long,
    ) {

}