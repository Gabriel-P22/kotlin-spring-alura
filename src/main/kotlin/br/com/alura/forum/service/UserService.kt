package br.com.alura.forum.service


import br.com.alura.forum.model.User
import org.springframework.stereotype.Service

@Service
class UserService(
    private var userList: List<User>
) {

    init {
        val user = User(
            name = "back-end training",
            id = 1,
            email = "email@email.com"
        );

        userList = listOf(user);
    }

    fun findUserById(id: Long): User {
        return userList.first { it.id == id }
    }

}