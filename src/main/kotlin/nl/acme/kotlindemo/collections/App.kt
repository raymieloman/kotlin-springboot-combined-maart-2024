package nl.acme.kotlindemo.collections


fun main() {
    val numbers = mutableListOf(1, 2, 3)

    println(numbers+4)
    println(numbers)
    var nieuwNumber = numbers + 10 // dit is een List<Int> dus immutable
}