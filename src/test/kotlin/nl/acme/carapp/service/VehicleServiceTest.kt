package nl.acme.carapp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.acme.carapp.model.Car
import nl.acme.carapp.dao.VehicleRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class VehicleServiceTest {


    val vehicleRepository: VehicleRepository = mockk() // mock
    val vehicleService = VehicleService(vehicleRepository) // class under test

    var car: Car;

    init {
        this.car = Car();
        this.car.mileage = 1000.5
        this.car.licensePlate = "AABBCC"
    }

    @Test
    fun testFindById() {
        
        //given
        every { vehicleRepository.findByIdOrNull(1) } returns car;

        //when
        val result = vehicleService.findById(1)

        //then
        verify(exactly = 1) { vehicleRepository.findByIdOrNull(1) }
        assertEquals(car, result)
    }
}