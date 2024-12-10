package br.com.alura.forum.integration

import br.com.alura.forum.dto.TopicPerCategory
import br.com.alura.forum.mock.TopicMock
import br.com.alura.forum.repository.TopicRepository
import org.assertj.core.api.Assertions.assertThat
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.domain.PageRequest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import kotlin.test.Test

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TopicRepositoryTest {

    @Autowired
    private lateinit var topicRepository: TopicRepository;

    private val topic = TopicMock.build();

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:8.0.28").apply {
            withDatabaseName("testdb")
            withUsername("test")
            withPassword("123456")
        }

        @JvmStatic
        @DynamicPropertySource
        fun properties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)

            registry.add("spring.flyway.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.flyway.user", mysqlContainer::getUsername)
            registry.add("spring.flyway.password", mysqlContainer::getPassword)
            registry.add("spring.flyway.locations") { "classpath:db/migration" }
        }
    }

    @Test
    fun `generate report`() {
        topicRepository.save(topic)
        val reports = topicRepository.report()

        assertThat(reports).isNotNull
        assertThat(reports.first()).isExactlyInstanceOf(TopicPerCategory::class.java)
    }

    @Test
    fun `generate topic per training name`() {
        topicRepository.save(topic)
        val topic = topicRepository.findByTrainingName("kotlin", PageRequest.of(0,5))
        assertThat(topic).isNotNull
    }

}