package nl.acme.carapp.api

import nl.acme.carapp.api.dto.CarDTO
import nl.acme.carapp.labels.Constants
import nl.acme.carapp.model.Car
import nl.acme.carapp.model.Vehicle
import nl.acme.carapp.service.VehicleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.net.URI

@RestController
@RequestMapping(Constants.uri)
class VehicleController(val vehicleService: VehicleService) {

    @Value("\${spring.application.name}")
    lateinit var name: String

    @GetMapping("demo/properties")
    fun showDemoReadingOrderPropertiesFiles() {
        println(this.name)
    }

    @PostMapping
    fun createVehicle(@RequestBody vehicle: Vehicle): ResponseEntity<CarDTO> {
        val vehicleCreated  = this.vehicleService.createVehicle(vehicle)
        val carDTO = CarDTO(vehicleCreated as Car)

        return ResponseEntity.created(URI(vehicleCreated.id.toString())).body(carDTO)
    }

    @GetMapping
    fun getAllCars(): List<Vehicle> {
        return this.vehicleService.getAllVehicles()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Vehicle) = id

    @PutMapping("{target}")
    fun updateById(@PathVariable target: Car, @RequestBody source: CarDTO): ResponseEntity<Vehicle> {
        target.licensePlate = source.licensePlate
        target.mileage = source.mileage

        return ResponseEntity.ok(this.vehicleService.update(target))
    }

    @DeleteMapping("{victim}")
    fun deleteById(@PathVariable victim: Vehicle): ResponseEntity<Unit> {
        victim.id?.let { this.vehicleService.deleteById(it) }

        return ResponseEntity.noContent().build()
    }

    @GetMapping("mileage/{mileage}")
    fun findByMileage(@PathVariable mileage: Double) = this.vehicleService.findByMileage(mileage)
}