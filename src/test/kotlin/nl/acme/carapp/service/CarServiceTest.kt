package nl.acme.carapp.service

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import nl.acme.carapp.model.Car
import nl.acme.carapp.persistence.CarRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.springframework.data.repository.findByIdOrNull

class CarServiceTest {


    val carRepository: CarRepository = mockk();
    val carService = CarService(carRepository);

    var car: Car;

    init {
        this.car = Car();
        this.car.mileage = 1000.5
        this.car.licensePlate = "AABBCC"
    }

    @Test
    fun testFindById() {
        
        //given
        every { carRepository.findByIdOrNull(1) } returns car;

        //when
        val result = carService.findById(1)

        //then
        verify(exactly = 1) { carRepository.findByIdOrNull(1) };
        assertEquals(car, result)
    }
}