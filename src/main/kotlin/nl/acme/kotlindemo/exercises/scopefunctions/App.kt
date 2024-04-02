package nl.acme.kotlindemo.exercises.scopefunctions

data class User(val id: Long, val name: String, var email: String?)

fun main() {
    val user = run {
        val id = 1L
        val name = "<NAME>"
        val email = "<EMAIL>"

        User(id, name, email)
    }
    user.run { println(user) }

    val emailOK = user.email?.let {
        val regexp = Regex("[a-zA-Z0-9]+@[a-zA-Z0-9]")
        if (it.matches(regexp)) {
            true
        } else {
            false
        }
    }

    if(emailOK == null || !emailOK) {
        user.email = "info@example.com"
    } else {
        // aha, je moet hier een else branch opnemen als je .also wilt gebruiken. 't Is maar dat u het weet!
    }.also { println(user.name) }
}