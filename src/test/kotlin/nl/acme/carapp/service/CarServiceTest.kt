package nl.acme.carapp.service

import nl.acme.carapp.persistence.CarRepository
import org.junit.jupiter.api.Test
import org.mockito.InjectMocks
import org.mockito.Mock

class CarServiceTest(@InjectMocks val service: CarService) {

    @Mock
    lateinit var repo: CarRepository

    @Test
    fun testCarServiceFoo() {
        val allCars = this.service.getAllCars()
        println(allCars.size)
    }

}