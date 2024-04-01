package nl.acme.carapp.api

import nl.acme.carapp.model.Car
import nl.acme.carapp.model.Vehicle
import nl.acme.carapp.service.VehicleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping("api/vehicles")
class VehicleController(val service: VehicleService) {

    @Value("\${spring.application.name}")
    lateinit var name: String

    @GetMapping("demo/properties")
    fun showDemoReadingOrderPropertiesFiles() {
        println(this.name)
    }

    @PostMapping
    fun createVehicle(@RequestBody vehicle: Vehicle): ResponseEntity<Vehicle> { // rloman refactor car to Vehicle
        val vehicleCreated  = this.service.createVehicle(vehicle)
//        val carDTO = CarDTO(carCreated.id, carCreated.licensePlate!!, carCreated.mileage)

        return ResponseEntity.created(URI(vehicleCreated.id.toString())).body(vehicle)
    }

    @GetMapping
    fun getAllCars(): MutableList<Vehicle> {
        return this.service.getAllVehicles()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<Vehicle> {
        val vehicle = this.service.findById(id)
        if (vehicle == null) {
            return ResponseEntity.notFound().build()
        } else {
            return ResponseEntity.ok(vehicle)
        }
    }

    @GetMapping("mileage/{mileage}")
    fun findByMileage(@PathVariable mileage: Double) = this.service.findByMileage(mileage)
}