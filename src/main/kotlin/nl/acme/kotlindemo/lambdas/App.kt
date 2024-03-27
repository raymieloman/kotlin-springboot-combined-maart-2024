package nl.acme.kotlindemo.lambdas


fun main() {
    val lambda: (String) -> Unit = { it -> println(it) }
    doSomeAndReturnNothing(lambda)

    val lambda2: (List<Int>) -> String = {
        var result = ""
        for (element in it) {
            result += element
        }
        // return element // fails since that would return from the method, we want this the result of the lambda hence the final statement in the block
        result
    }
    val names = doSomeAndReturnSome(lambda2)
    println("Names: ${names}")
}

fun doSomeAndReturnNothing(lambda: (String) -> Unit) { // syntax: (parameterList) -> returnType
    lambda("Raymie")
}

fun doSomeAndReturnSome(lambda: (List<Int>) -> String): String {
    return lambda(listOf(7, 8, 9))
}


// using typealias
typealias DoSomeAndReturnSome = (List<Int>) -> String
fun doSomeAndReturnSomeDemoTypeAlias(lambda: DoSomeAndReturnSome): String {
    return lambda(listOf(7, 8, 9))
}
