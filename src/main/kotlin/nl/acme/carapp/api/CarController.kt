package nl.acme.carapp.api

import jakarta.annotation.Resource
import nl.acme.carapp.model.Car
import nl.acme.carapp.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/cars")
class CarController(val service: CarService) {

    @PostMapping
    fun creatCar(@RequestBody car: Car) : Car {
        return this.service.createCar(car)
    }

    @GetMapping
    fun getAllCars(): List<Car> {
        return this.service.getAllCars()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): Car? {
        return this.service.findById(id)
    }
}