package nl.acme.kotlindemo.exercises.transform

import nl.acme.kotlindemo.utils.Assert.Companion.assertEquals

data class Book(val title: String, val author: String, val publicationYear: Int)

fun main() {
    // Given
    val book1 = Book("The Hobbit", "Wie?", 2005)
    val book2 = Book("Almelo in 1968", "Herman Finkers", 1970)
    val book3 = Book("Zomaar", "Herman Finkers", 1960)

    val books = listOf(book1, book2, book3)

    // When
    val bookNames = books.map { it.title }
    val books2005 = books.filter { it.publicationYear == 2005 }
    val booksByAuthor = books.groupBy { it.author }

    // Then
    println(bookNames)
    println(books2005)
    println(booksByAuthor)

    assertEquals(1, books2005.size)
    assertEquals(2, booksByAuthor.size)
}
