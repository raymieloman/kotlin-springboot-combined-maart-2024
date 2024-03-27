package nl.acme.carapp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfiguration {

    @Bean
    fun webClient(builder: WebClient.Builder): WebClient =
        builder.baseUrl("http://jsonplaceholder.typicode.com").build()
}