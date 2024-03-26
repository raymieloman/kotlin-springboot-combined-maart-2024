package nl.acme.carapp

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class CarappApplicationTests {

	@Value("\${spring.application.name}")
	lateinit var name: String;

	@Test
	fun contextLoads() {
		println(this.name)
	}
}
