package nl.acme.carapp.kotlindemo.transformations

import nl.acme.carapp.utils.Assert.Companion.assertEquals

fun main() {

    fourth()

}

fun first() {
    val list = listOf(0, 1, 1, 2, 3, 5)
    list.map({ it * it }).forEach({ println(it) })
}

fun second() {
    val list = listOf(0, 1, 1, 2, 3, 5)
    list.map { it * it }.forEach { println(it) }
}

fun third() {
    val list = listOf(0, 1, 1, 2, 3, 5)
    list.filter { it > 3 }.forEach { println(it) }
}

fun fourth() { // flatMap
    val list = listOf("alfa", "beta", "charly")
    // know that String::toList is a function
    val name = "Raymond"
    val listOfName = name.toList()
    println(listOfName)
    // then we can flatMap it using this
    val letters = list.flatMap { it.toList() }
    println(letters)

    // test myzelf :-)
    assertEquals("start => alfabetacharly <= eind", letters.joinToString(prefix="start => ", postfix = " <= eind", separator = "") { ""+it })
}