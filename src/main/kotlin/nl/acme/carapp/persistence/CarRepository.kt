package nl.acme.carapp.persistence

import nl.acme.carapp.model.Car
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: CrudRepository<Car, Long>