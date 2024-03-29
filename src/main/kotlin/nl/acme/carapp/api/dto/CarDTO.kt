package nl.acme.carapp.api.dto

open class CarDTO(val id: Long?, val licensePlate: String, val mileage: Double, val type: String = "car")
class TruckDTO(id: Long?, licensePlate: String, mileage: Double, val maxWeight: Double, type: String = "truck"): CarDTO(id, licensePlate,mileage,type)



