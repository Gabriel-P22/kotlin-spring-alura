package br.com.alura.forum.dto

data class TopicForm(
    val id: Long? = null,
    val title: String,
    val message: String,
    val idCourse: Long,
    val idUser: Long,
    ) {

}