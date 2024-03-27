package nl.acme.carapp.integrationtests

import jakarta.annotation.PostConstruct
import nl.acme.carapp.model.Car
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName::class)
class CarAppControllerIT {

    constructor() {
        println("Constructor invoked") // remember this is invoked for EVERY test
    }

    companion object {
        var id: Long = 0; // to make a 'static' var
    }

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
    fun when1PostCar() {
        // Given
        val car = Car()
        car.mileage = 15.0
        car.licensePlate = "AABBCC" // remove this to see the integration test in action :-)

        // When
        val result = testRestTemplate.postForEntity(baseApi, car, Car::class.java);
        id = result.body?.id!!


        // Then
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result?.statusCode)
    }


    @Test
    fun when2GetByIdThenOK() {
        val result = testRestTemplate.getForEntity("${baseApi}/${id}", Car::class.java)

        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(15.0, result?.body?.mileage);
    }
}
