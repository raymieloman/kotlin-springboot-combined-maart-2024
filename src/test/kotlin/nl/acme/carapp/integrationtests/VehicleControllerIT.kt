package nl.acme.carapp.integrationtests

import jakarta.annotation.PostConstruct
import nl.acme.carapp.api.dto.CarDTO
import nl.acme.carapp.api.dto.TruckDTO
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
import org.springframework.test.annotation.DirtiesContext
import org.springframework.test.context.ActiveProfiles

@ActiveProfiles("integrationtest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.MethodName::class)
class VehicleControllerIT {

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
        val api = "api/vehicles"
        baseApi = String.format("http://%s:%d/%s", host, localPort, api)
    }

    @Test
    fun when1PostCar() {
        // Given
        val car = CarDTO(null, "AABBCC", 15.0)

        // When
        val result = testRestTemplate.postForEntity(baseApi, car, CarDTO::class.java);
        id = result.body?.id!!

        // Then
        assertNotNull(result)
        assertEquals(HttpStatus.CREATED, result?.statusCode)
    }


    @Test
    @DirtiesContext
    fun when2GetByIdThenOK() {
        // When
        val result = testRestTemplate.getForEntity("${baseApi}/${id}", CarDTO::class.java)

        // Then
        assertNotNull(result)
        assertEquals(HttpStatus.OK, result?.statusCode)
        assertEquals(15.0, result?.body?.mileage);
    }

    @Test
    fun when3PostTruck() {
        // Given
        val truckDtoIn = TruckDTO(null, "AABBCC", 15.0, 15000.0, "truck")

        // When
        val response = testRestTemplate.postForEntity(baseApi, truckDtoIn, TruckDTO::class.java);
        val truckDtoOut = response.body
        id = truckDtoOut?.id!!

        // Then
        assertNotNull(truckDtoOut)
        assertEquals(HttpStatus.CREATED, response?.statusCode)
    }

    @Test
    fun when4GetByIdThenOK() {
        // When
        val response = testRestTemplate.getForEntity("${baseApi}/${id}", TruckDTO::class.java)

        // Then
        assertNotNull(response)
        assertEquals(HttpStatus.OK, response?.statusCode)

        val truckResult = response.body!! // in test the !! (not null assertion operator) is allowed!!!
        assertNotNull(truckResult)
        assertEquals(15.0, truckResult.mileage);
        assertEquals("truck", truckResult.type)
        assertEquals(15000.0, truckResult.maxWeight)
    }
}