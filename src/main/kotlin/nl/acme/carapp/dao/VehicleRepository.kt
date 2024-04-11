package nl.acme.carapp.dao

import nl.acme.carapp.model.Car
import nl.acme.carapp.model.Vehicle
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface VehicleRepository: JpaRepository<Vehicle, Long> {

    fun findByLicensePlate(licensePlate: String): List<Car>

    /*
    @Query("SELECT r FROM RelationDAO r WHERE r.person2.id = :id AND r.relationship IN :relations")
fun findParents(id: Long, relations: Set<Relationship>,
): List<RelationDAO>
     */
    @Query("select c from Car c where c.mileage = :mileage")
    fun findByMileageCustomQuery(mileage: Double) : List<Car>

    @Query(value="select * from Car c where c.mileage = :mileage", nativeQuery = true)
    fun findByMileageCustomQueryNative(mileage: Double) : List<Car>
}

