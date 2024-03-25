package nl.acme.carapp.kotlindemo.nulls

fun main() {

    var name: String? = null;

    println(name)
    if (name != null) {
        println(name.length)
    }
    name = "Frans"

    name?.also{ println(name.length)}

    name?.let{ println(name.length)}.also { print(5)}
}

fun foo(name: String?): String {
    if (name == null) {
        return "";
    }
    // Kotlin weet nu dat name gevuld is
    println(name.length)

    return name;
}