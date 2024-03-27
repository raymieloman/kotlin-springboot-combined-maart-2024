package nl.acme.kotlindemo.starprojections

import nl.acme.carapp.model.Car

fun main() {
    val listElement = printList(listOf(1, 3.5, "String", Car()))

    when (listElement) {
        is Double -> {
            println("Het is een double: ${listElement}")
        }

        is String -> {
            println("Het is een String: ${listElement}")
        }
    }
}

fun printList(list: List<*>): Any? {
    println(list)
//    list.addFirst("") // faalt want het is geen consumer!
    return list.get(0) // faalt (althans, er komt een Any uit) want het is geen producer (dus * is geen consumer en geen producer maar kan inderdaad alles ontvangen)
}