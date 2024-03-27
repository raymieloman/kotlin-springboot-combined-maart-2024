package nl.acme.carapp.config

import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MyTestBeans {

    @Bean
    fun restTemplate(): TestRestTemplate {
        return TestRestTemplate()
    }
}