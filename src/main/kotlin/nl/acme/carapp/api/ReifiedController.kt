package nl.acme.carapp.api

import nl.acme.carapp.model.Vehicle
import nl.acme.carapp.service.VehicleService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/reifieddemo")
@Validated
class ReifiedController(private val service: VehicleService) {

    @PostMapping
    fun reifiedDemo(@RequestBody car: Vehicle): Vehicle {
        determineType<Vehicle>(car)
        return car;
    }

    /**
     * Again, reified is just a stronger version than Java regarding type erasure
     * now, we can see ans ask for which type it is, that is impossible in Java
     * And you do not have supply the classType in het method signature
     */
    private inline fun <reified T> determineType(obj: Any): Any {
        if (obj is T) {
            println("Object is of type ${T::class.simpleName}")
        } else {
            println("Object is not of type ${T::class.simpleName}")
        }
        /*
        old
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
        */
        return obj
    }
}