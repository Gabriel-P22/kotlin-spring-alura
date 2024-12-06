package br.com.alura.forum.mock

import br.com.alura.forum.model.User

object UserMock {
    fun build() = User(
        id = 1,
        name = "Training name",
        email = "email@test.com",
        password = "password123"
    );
}