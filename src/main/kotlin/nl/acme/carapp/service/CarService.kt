package nl.acme.carapp.service

import jakarta.transaction.Transactional
import nl.acme.carapp.config.Employee
import nl.acme.carapp.model.Car
import nl.acme.carapp.persistence.CarRepository
import nl.acme.carapp.utils.Assert.Companion.assertEquals
import nl.acme.carapp.utils.CarOwner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

/*
We fix the ambiguous bean issue by choosing between two options
1. Using the qualifier with the name (which is the name of the method or the bean name)
2. Using the @Primary annotation when defining the bean

Adddional:
to show that the beanName can be different than the method name, see bean with name johnEmployee in EmployeeBeans and see the @Qualifier hier!
 */

@Service
class CarService(val repository: CarRepository, val bmw: Car, val jan: CarOwner, /*@Qualifier("JohnEmployee")*/ val employee: Employee,  @Qualifier("johnEmployee") val johnEmployee: Employee) {

    init {
        this.repository.save(bmw)
        println("Car breated ${bmw} for owner: ${this.jan}")
        assertEquals("John", this.johnEmployee.name)
    }

    fun getAllCars(): List<Car> {
        return this.repository.findAll()
    }

    @Transactional
    fun createCar(car: Car) = this.repository.save(car)


    fun findById(id: Long): Car? = this.repository.findById(id).getOrNull()

    fun findByMileage(mileage: Double) = this.repository.findByMileageCustomQueryNative(mileage)
}
