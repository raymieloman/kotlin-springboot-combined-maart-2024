package nl.acme.kotlindemo.utils


fun String.countVowels(): Int {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    return count { element -> element in vowels }
}

