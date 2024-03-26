package nl.acme.carapp.kotlindemo.localfunctions

fun main() {
    hanoi(3)
}

fun hanoi(n: Int){
    fun hanoi(n: Int, van: Int, via: Int, naar: Int) {
        if (n > 0) {
            hanoi(n - 1, van, naar, via)
            println("Move $van to $naar")
            hanoi(n - 1, via, van, naar)
        }
    }

    hanoi(n, 1, 2, 3)
}

