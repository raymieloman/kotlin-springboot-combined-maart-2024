package nl.acme.kotlindemo.closures

import nl.acme.carapp.utils.Assert.Companion.assertEquals

fun main() {
    val myIncrementer2 = createIncrementer(2)
    val myIncrementer3: () -> Int = createIncrementer(3)
    val my2 = myIncrementer2()
    val my4 = myIncrementer2()
    val my3 = myIncrementer3()

    println(my2)
    println(my3)
    println(my4)

    val lambda: (Int) -> Int = fun(n: Int): Int {
        return n * n
    }

    println(lambda)
    println(lambda(4))
    assertEquals(16, lambda(4))
}

/*
Be clear that this function returns a lambda, and that lambda knows the counter and the incrementBy hence it is a closure, it closes over incrementBy and counter
 */
fun createIncrementer(incrementBy: Int): () -> Int {
    var counter = 0

    val incrementer = {
        counter += incrementBy
        counter
    }

    return incrementer
}


