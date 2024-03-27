package nl.acme.carapp.api

import jakarta.annotation.Resource
import nl.acme.carapp.model.Car
import nl.acme.carapp.service.CarService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.jpa.domain.AbstractPersistable_.id
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("api/cars")
class CarController(val service: CarService) {

    @Value("\${spring.application.name}")
    lateinit var name: String

    @GetMapping("demo/properties")
    fun showDemoReadingOrderPropertiesFiles() {
        println(this.name)
    }

    @PostMapping
    fun creatCar(@RequestBody car: Car): ResponseEntity<Car> {
        val carCreated  = this.service.createCar(car)

        return ResponseEntity.created(URI(carCreated.id.toString())).body(carCreated)
    }

    @GetMapping
    fun getAllCars(): List<Car> {
        return this.service.getAllCars()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Car> {
        val car = this.service.findById(id)
        if (car == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(car)

        }
    }

    @GetMapping("mileage/{mileage}")
    fun findByMileage(@PathVariable mileage: Double) = this.service.findByMileage(mileage)
}