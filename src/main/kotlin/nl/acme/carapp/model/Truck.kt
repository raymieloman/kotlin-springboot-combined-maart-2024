package nl.acme.carapp.model

import jakarta.persistence.Entity

@Entity
class Truck: Car() {

    var name: String = "";
    var maxWeight: Double = 0.0

    override fun type() = "truck"
}