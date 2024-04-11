package nl.acme.carapp.integrationtests

import nl.acme.carapp.api.dto.CarDTO
import nl.acme.carapp.integrationtests.mockmvc.AbstractApiIT
import nl.acme.carapp.labels.Constants.Companion.uri
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@ActiveProfiles("integrationtest")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class VehicleControllerApiIT : AbstractApiIT() {

    val carFixture = CarDTO(id = null, mileage = 1200.0, licensePlate = "AABBCC")

    @Test
    @Order(1)
    fun createCar() {
        // When
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.post(uri).content(mapToJson(carFixture))
                .accept(MediaType.APPLICATION_JSON_VALUE).header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Then
        val status = mvcResult.response.status
        assertEquals(201, status)

        val content = mvcResult.response.getContentAsString()
        val vehicles: CarDTO = super.mapFromJson(content)
        assertEquals(1, vehicles.id)
    }

    @Test
    @Order(2)
    fun getVehicleList() {
        // Act
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Assert
        val status = mvcResult.response.status
        assertEquals(200, status)

        val content = mvcResult.response.getContentAsString()
        val vehicles: ArrayList<CarDTO> = super.mapFromJson(content)
        assertEquals(1, vehicles.size)
        assertEquals(1L, vehicles.get(0).id!!)
    }

    @Test
    @Order(3)
    fun getVehicle() {
        // Act
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Assert
        val status = mvcResult.response.status
        assertEquals(200, status)

        val content = mvcResult.response.contentAsString
        val car: CarDTO = super.mapFromJson(content)
        assertEquals(1, car.id)
        assertEquals(carFixture.licensePlate, car.licensePlate)
        assertEquals(carFixture.type, car.type)
        assertEquals(carFixture.mileage, car.mileage)
    }

    @Test
    @Order(4)
    fun updateVehicle() {
        // Arrange
        val newCar = CarDTO(null, "DDEEFF", 23456.0)
        // Act
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.put(uri).content(mapToJson(newCar)).header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Assert
        val status = mvcResult.response.status
        assertEquals(200, status)

        val content = mvcResult.response.contentAsString
        val car: CarDTO = super.mapFromJson(content)
        assertEquals(1, car.id)
        assertEquals(newCar.licensePlate, car.licensePlate)
        assertEquals(newCar.type, car.type)
        assertEquals(newCar.mileage, car.mileage)
    }

    @Test
    @Order(5)
    fun deleteVehicle() {
        // Arrange

        // Act
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Assert
        val status = mvcResult.response.status
        assertEquals(204, status)

        val content = mvcResult.response.contentAsString
        assertEquals("", content)
    }

    @Test
    @Order(6)
    fun checkDeletion() {
        // Act
        val mvcResult = mockMvc.perform(
            MockMvcRequestBuilders.delete(uri)
                .accept(MediaType.APPLICATION_JSON_VALUE)
        ).andReturn()

        // Assert
        val status = mvcResult.response.status
        assertEquals(404, status)

        val content = mvcResult.response.contentAsString
        assertEquals("The id is missing", content)
    }
}