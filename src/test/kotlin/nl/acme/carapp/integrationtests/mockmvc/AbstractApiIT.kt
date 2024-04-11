package nl.acme.carapp.integrationtests.mockmvc

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc


@WebAppConfiguration
@AutoConfigureMockMvc
@SpringBootTest
abstract class AbstractApiIT {

    @Autowired
    protected lateinit var mockMvc: MockMvc

    fun <T> mapToJson(obj: T): String {
        val objectMapper = ObjectMapper()

        return objectMapper.writeValueAsString(obj)
    }

    final inline fun <reified T> mapFromJson(json: String): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(json, object : TypeReference<T>() {})
    }
}