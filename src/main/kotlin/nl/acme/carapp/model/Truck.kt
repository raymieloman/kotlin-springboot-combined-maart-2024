package nl.acme.carapp.model

import jakarta.persistence.Entity

@Entity
class Truck: Car() {

    open var name: String = "";

    override fun type(): String {
        return "truck"
    }
}