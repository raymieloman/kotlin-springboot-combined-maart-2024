package nl.acme.kotlindemo.nulls

fun main() {
    val name: String? = if(Math.random() * 10 > 5) "MyName" else  null;
    println(name)
    if (name != null) { // in effect Kotlin smart casts this from String? to String
        println(name.length)
    }
    name?.also{ println(name.length)}
    name?.let{ println(name.length)}.also { print(5)}
    foo(name)
}

fun foo(name: String?): String {
    if (name == null) {
        return "";
    }
    // Kotlin weet nu dat name gevuld is
    println(name.length)

    return name;
}