package nl.acme.carapp

fun main() {
    hanoi(2)
}

fun hanoi(n: Int){
    fun hanoi(n: Int, van: Int, via: Int, naar: Int) {
        if (n > 0) {
            hanoi(n - 1, van, naar, via)
            println("Move disk $n from $van to $naar")
            hanoi(n - 1, via, van, naar)
        }
    }

    hanoi(n, 1, 2, 3)
}

