package nl.acme.carapp.api

import nl.acme.carapp.model.AbstractCar
import nl.acme.carapp.model.Car
import nl.acme.carapp.model.Truck
import nl.acme.carapp.service.CarService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reifieddemo")
@Validated
class ReifiedController(private val service: CarService) {

    @PostMapping
    fun reifiedDemo(@RequestBody car: AbstractCar): AbstractCar {
        var myCar = determineType(car)
        return car;
//        return this.service.createCar(myCar as)
    }

    /**
     * Again, reified is just a stronger version than Java regarding type erasure
     * now, we can see ans ask for which type it is, that is impossible in Java
     */
    private inline fun <reified T> determineType(t: T): T {
        println("The posted type is: ${t!!::class.java}")
        when (t) {
            is Car -> {
                println("The posted type is: Car")
                return t
            }

            is Truck -> {
                println("The posted type is: Truck")
                return t;
            }

            else -> {
                throw IllegalArgumentException();
            }
        }
    }
}