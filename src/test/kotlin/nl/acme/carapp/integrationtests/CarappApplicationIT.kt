package nl.acme.carapp.integrationtests

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("integrationtest")
class CarappApplicationIT {

	@Test
	fun contextLoads() {
	}

}
