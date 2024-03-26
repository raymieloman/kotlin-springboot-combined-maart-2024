package nl.acme.carapp.integrationtests

import jakarta.annotation.PostConstruct
import nl.acme.carapp.model.Car
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

//@SpringBootTest(classes = arrayOf(CarappApplication::class),)
@ActiveProfiles("integrationtest")
//@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarAppControllerIT {

    @Autowired
    lateinit var testRestTemplate: TestRestTemplate

    @LocalServerPort
    private val localPort = 0

    private var baseApi: String? = null

    @PostConstruct
    fun postConstruct() {
        val host = "localhost"
        val api = "api/cars"
        baseApi = String.format("http://%s:%d/%s", host, localPort, api)
    }


    @Test
    fun whenGetCalled_thenShouldBadReqeust() {
        val result = testRestTemplate.getForEntity(baseApi+"/1", Car::class.java);

        assertNotNull(result)
        assertEquals(HttpStatus.BAD_REQUEST, result?.statusCode)
    }
}