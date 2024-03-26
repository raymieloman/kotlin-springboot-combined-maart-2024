package nl.acme.carapp.persistence

import nl.acme.carapp.model.Car
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CarRepository: JpaRepository<Car, Long>