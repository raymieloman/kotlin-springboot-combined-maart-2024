package nl.acme.carapp.kotlindemo.classesandconstructors.dataclass

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

    bird.name = " value ";

    // copy
    val bird3 = bird.copy() // full copy
    val bird4 = bird.copy(age = 45) // partial copy



    // data classes zijn dus eigenlijk vooral bedoeld als data class
    // dus geen utility class of een class waarvan je kunt erven.

    assert(bird.name == "Poedel")


    bird.name = "Raaf"
//    bird.age++;
}

