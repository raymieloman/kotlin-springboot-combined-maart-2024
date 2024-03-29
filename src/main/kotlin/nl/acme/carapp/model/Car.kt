package nl.acme.carapp.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class Car: Vehicle(){

    @Column(nullable = false, length = 10)
    open var licensePlate: String? = null

    open var mileage = 0.0

    open override fun type() = "car"


}