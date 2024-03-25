package nl.acme.carapp.kotlindemo.classesandconstructors

open class Car(val licensePlate: String) {

    var mileage: Double = 0.0

    // secondary constructor
    constructor() : this("onbekend") { // aanroep van de primary constructor
        this.mileage = 3.5
    }

    constructor(mileage: Double): this("onbekend") {
        this.mileage = mileage
    }
}

open class Truck(licensePlate: String) : Car(licensePlate) {
}

class Lorry(licensePlate: String) : Truck(licensePlate) {

}

fun main() {
    val car = Lorry("PS-49-AB")

    println(car.licensePlate)
    println(car.licensePlate.length)


}

