package nl.acme.kotlindemo.starprojections

import nl.acme.carapp.model.Car

fun main() {
    println(listOf(1,3.5, "String", Car()))

}



fun printList(list: List<*>) {
    println(list)
//    list.addFirst("") // faalt want het is geen consumer!
//    return list.get(0) // faalt want het is geen producer (dus * is geen consumer en geen producer maar kan inderdaad alles ontvangen)
}