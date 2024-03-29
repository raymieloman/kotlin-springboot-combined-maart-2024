package nl.acme.carapp.service

import jakarta.transaction.Transactional
import nl.acme.carapp.model.Vehicle
import nl.acme.carapp.persistence.VehicleRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class VehicleService(val repository: VehicleRepository) {

    fun <T: Vehicle> getAllVehicles(): List<T> {
        return this.repository.findAll() as List<T>
    }

    @Transactional
    fun createVehicle(vehicle: Vehicle) = this.repository.save(vehicle)
    fun findById(id: Long): Vehicle? = this.repository.findById(id).getOrNull()
    fun findByMileage(mileage: Double) = this.repository.findByMileageCustomQueryNative(mileage)
}
