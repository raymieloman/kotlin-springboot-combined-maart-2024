package nl.acme.carapp.kotlindemo.scopefunctionsdemo

import nl.acme.carapp.utils.Assert.Companion.assertEquals
import kotlin.math.sqrt

data class Car(val licensePlate: String, var mileage: Int = 0)



fun main() {
    val car = Car("1234")
    // with is a scope function that takes an object and a lambda as arguments. and returns the final expression
    // scope: this
    val a = with(car) {
        // and scope is now 'this'
        println(licensePlate)
        "francien"
    }
    println(car.licensePlate)
    // apply is a scope function that takes an object and a lambda as arguments. and returns the object
    val b = Car("1234").apply {
        mileage = 14
        println(mileage)
        "poedel" // still returns Car // or implicity returns this
    }
    val alsothis = Car("1234").also {
        println(it.licensePlate)
        println(sqrt(2.0))
        "francien" // still returns car
    }
    // eh ... just to validate for null
    val someCar: Car? = Car("testit")
    someCar?.let {
        println(it.licensePlate)
    }
    // for local blocks and combine with with let (no pun intended)
    run {
        println("Why would I run this?")
        println("The answer: to (re) use a variable name")
        val a = 1;
        assertEquals(1, a)
        // be clear that this in invalid in Java and OK in Kotlin.
        run {
            val a = 3
            assertEquals(3, a)
        }
        run {
            val a = 4
            assertEquals(4, a)
        }
        // is a different one.
    }
}