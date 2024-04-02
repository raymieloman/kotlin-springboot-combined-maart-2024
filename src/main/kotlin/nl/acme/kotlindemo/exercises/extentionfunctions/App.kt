package nl.acme.kotlindemo.exercises.extentionfunctions

import nl.acme.kotlindemo.utils.Assert.Companion.assertEquals


fun main() {
    val numbers = listOf(1, 2, 3, 4, 5, 6, 7)
    val evenNumbers = numbers.sumOfEvenNumbers()
    assertEquals(12, evenNumbers)
}

fun List<Int>.sumOfEvenNumbers(): Int {
    var sum = 0
    for (element in this) {
        if (element % 2 == 0) {
            sum += element
        }
    }

    return sum;
}