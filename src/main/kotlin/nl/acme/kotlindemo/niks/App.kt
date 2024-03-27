package nl.acme.kotlindemo.niks

fun main() {
    nothing();
}

fun nothing(): Nothing {
    println("niks")

    throw Exception()

}
