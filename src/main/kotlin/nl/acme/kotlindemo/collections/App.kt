package nl.acme.kotlindemo.collections

import nl.acme.kotlindemo.utils.Assert.Companion.assertEquals


fun main() {
    val numbers = mutableListOf(1, 2, 3)

    println(numbers+4)
    println(numbers)
    var numbersPlusTen = numbers + 10 // dit is een List<Int> dus immutable

    assertEquals(listOf(1,2,3,10), numbersPlusTen)
}