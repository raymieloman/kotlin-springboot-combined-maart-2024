package nl.acme.carapp.kotlindemo.abstracting

interface Drivable {
    fun drive(): Int
}


abstract class Vehicle: Drivable {
    abstract fun drive(delta: Int): Int
}

interface Driver {

}

class Car: Vehicle(), Driver  {

    var mileage = 0

    override fun drive() = ++this.mileage

    override  fun drive(delta: Int): Int {
        this.mileage += delta;
        return this.mileage
    }
}
