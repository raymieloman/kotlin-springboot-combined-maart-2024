package nl.acme.carapp.service

import jakarta.transaction.Transactional
import nl.acme.carapp.model.Car
import nl.acme.carapp.persistence.CarRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class CarService(val repository: CarRepository) {

    fun getAllCars(): List<Car> {
        return this.repository.findAll()
    }

    @Transactional
    fun createCar(car: Car) = this.repository.save(car)
    fun findById(id: Long): Car? = this.repository.findById(id).getOrNull()
    fun findByMileage(mileage: Double) = this.repository.findByMileageCustomQueryNative(mileage)
}
