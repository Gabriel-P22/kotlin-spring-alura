package br.com.alura.forum.controller

import br.com.alura.forum.config.jwt.JwtConfiguration
import br.com.alura.forum.model.Role
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import kotlin.test.Test

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TopicControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var jwtUtil: JwtConfiguration

    private var token: String? = ""

    companion object {
        private const val RESOURCE = "/topic"
        private const val RESOURCE_ID = RESOURCE.plus("/%s")
    }

    @BeforeEach
    fun setup() {
        token = generateToken();

        mockMvc = MockMvcBuilders
            .webAppContextSetup(webApplicationContext)
            .apply<DefaultMockMvcBuilder?>(
                SecurityMockMvcConfigurers.springSecurity()
            ).build()
    }

    @Test
    fun `return error 400 when call topics without token`() {
        mockMvc.get(RESOURCE).andExpect { status { is4xxClientError() } }
    }

    @Test
    fun `return success when call topics`() {
        mockMvc.get(RESOURCE) {
            headers {
                token?.let { this.setBearerAuth(it) }
            }
        }.andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `return success when call topics with id`() {
        mockMvc.get(RESOURCE_ID.format("1")) {
            headers {
                token?.let { this.setBearerAuth(it) }
            }
        }.andExpect { status { is2xxSuccessful() } }
    }

    private fun generateToken(): String? {
        val authorities = mutableListOf(Role(1, "READ_AND_WRITE"))
        return jwtUtil.generateToken("email@email.com", authorities)
    }

}