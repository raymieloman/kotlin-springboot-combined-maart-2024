package nl.acme.carapp.api.dto

import nl.acme.carapp.labels.Constants
import nl.acme.carapp.model.Car

open class CarDTO(val id: Long?, var licensePlate: String, var mileage: Double, val type: String = Constants.car) {
    constructor(car: Car) : this(car.id, car.licensePlate ?: Constants.unknown, car.mileage)

    // this constructor is used by Jackson
    constructor() : this(null, Constants.unknown, 0.0)
}

class TruckDTO(id: Long?, licensePlate: String, mileage: Double, val maxWeight: Double, type: String = Constants.truck) :
    CarDTO(id, licensePlate, mileage, type)



