package nl.acme.carapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CarappApplication

fun main(args: Array<String>) {
	runApplication<CarappApplication>(*args)
}
