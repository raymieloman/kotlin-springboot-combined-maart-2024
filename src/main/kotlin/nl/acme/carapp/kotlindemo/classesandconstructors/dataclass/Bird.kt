package nl.acme.carapp.kotlindemo.classesandconstructors.dataclass

import nl.acme.carapp.utils.Assert.Companion.assertEquals

open class Animal

data class Bird(var name: String, val age: Int): Animal()


fun main() {
    val bird = Bird("Vink", 3)

    // toString demo
    println(bird)

    val bird2 = Bird("Vink", 3)
    println(bird.equals(bird2))

    println(bird.component1())
    println(bird.component2())

    // destructuring
    val (myname, myage) = bird

    println(myname)
    println(myage)

    val p = Pair(1, "Raymond")
    assertEquals(1, p.first)
    assertEquals("Raymond", p.second)

    bird.name = " value ";

    // copy
    val bird3 = bird.copy() // full copy
    assertEquals(3, bird3.age)
    val bird4 = bird.copy(age = 45) // partial copy
    assertEquals(45, bird4.age)



    // data classes zijn dus eigenlijk vooral bedoeld als data class
    // dus geen utility class of een class waarvan je kunt erven.

    bird.name = "Raaf"
//    bird.age++; // faalt want age is hier een val(ue)
}

