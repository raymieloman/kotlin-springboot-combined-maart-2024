package nl.acme.kotlindemo.scopefunctionsdemo

import nl.acme.kotlindemo.utils.Assert.Companion.assertEquals
import kotlin.math.sqrt

data class Car(val licensePlate: String, var mileage: Int = 0)


fun main() {
    var car = Car("1234")
    // with is a scope function that takes an object and a lambda as arguments. and returns the final expression
    // scope: this
    val someName = with(car) {
        // and scope is now 'this'
        println(licensePlate)
        "francien"
    }
    assertEquals("francien", someName)
    println(car.licensePlate)
    // apply is a scope function that takes an object and a lambda as arguments. and returns the object
    val b = Car("1234").apply {
        mileage = 14
        println(mileage)
    }
    assertEquals(14.0, b.mileage)
    car = Car("AA-BB-99").also {
        println(it.licensePlate)
        println(sqrt(2.0))
    }
    assertEquals("AA-BB-99", car.licensePlate)
    // eh ... just to validate for null
    val someCar: Car? = Car("testit")
    someCar?.let {
        println(it.licensePlate)
    }
    // for local blocks and combine with with let (no pun intended)
    run {
        println("Why would I run this?")
        println("The answer: to (re) use a variable name")
        run {
            val a = 1;
            assertEquals(1, a)
        }
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