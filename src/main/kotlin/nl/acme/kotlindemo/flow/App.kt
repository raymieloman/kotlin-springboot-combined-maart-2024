package nl.acme.java2kotlin.democode.flow

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.take
import java.math.BigInteger

suspend fun main() {
    println("== Fibonacci")
    fibonacci().take(5).collect { println(it) }
    println("== Lukas")
    lukas(3,4).take(10).collect { println(it) }
}


fun fibonacci(): Flow<BigInteger> = flow {
    var x = BigInteger.ZERO
    var y = BigInteger.ONE
    while (true) {
        println("Emitting: ${x}")
        emit(x)
        x = y.also {
            y += x
        }
    }
}

fun lukas(a: Int, b:Int): Flow<BigInteger> = flow {
    var a1:BigInteger = a.toBigInteger();
    var b1: BigInteger = b.toBigInteger()
    while(true) {
        println("Emitting: $a1")
        emit(a1)
        a1 = b1.also {
            b1 = a1 + b1
        }
    }
}

