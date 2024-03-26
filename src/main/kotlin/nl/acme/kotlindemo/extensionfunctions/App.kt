package nl.acme.kotlindemo.extensionfunctions

import nl.acme.carapp.utils.countVowels

fun main() {
    val text = "Hello, Kotlin"
    val vowelCount = text.countVowels() // Calls the extension function
    println("Number of vowels: $vowelCount") // Output: Number of vowels: 4


}
