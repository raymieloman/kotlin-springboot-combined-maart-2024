package nl.acme.carapp.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
open class Car: AbstractCar(){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    open var id: Long? = null

    @Column(nullable = false, length = 10)
    open var licensePlate: String? = null

    open var mileage = 0.0

    override var type = "car"
}