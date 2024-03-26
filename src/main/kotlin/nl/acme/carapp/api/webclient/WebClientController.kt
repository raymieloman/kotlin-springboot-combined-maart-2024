package nl.acme.carapp.api.webclient

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import nl.acme.carapp.model.Car
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.ClientResponse
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.WebClientResponseException
import reactor.core.publisher.Mono
import reactor.netty.http.client.HttpClient
import java.nio.charset.StandardCharsets
import java.time.Duration
import java.time.ZonedDateTime
import java.util.*
import java.util.concurrent.TimeUnit


@RestController
@RequestMapping("api/webclient")
class WebClientController {

    @GetMapping
    fun getMe() {
//        val client = WebClient.create()

//        val client = WebClient.create("http://localhost:8080")

        val httpClient: HttpClient = HttpClient.create()
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 5000)
            .responseTimeout(Duration.ofMillis(5000))
            .doOnConnected { conn ->
                conn.addHandlerLast(ReadTimeoutHandler(5000, TimeUnit.MILLISECONDS))
                    .addHandlerLast(WriteTimeoutHandler(5000, TimeUnit.MILLISECONDS))
            }

        /*
        val client = WebClient.builder()
            .baseUrl("http://localhost:8080")
            .defaultCookie("cookieKey", "cookieValue")
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .defaultUriVariables(Collections.singletonMap("url", "http://localhost:8080"))
            .build()
         */

        val client = WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()

        val uriSpec: WebClient.UriSpec<WebClient.RequestBodySpec> = client.post()
        val bodySpec: WebClient.RequestBodySpec = uriSpec.uri("http://localhost:8080/api/cars") // cat facts?

        val car = Car()
        car.mileage = 45.0
        car.licensePlate = "AABBCC"


        val headersSpec: WebClient.RequestHeadersSpec<*> = bodySpec.bodyValue(car)

        val responseSpec: WebClient.ResponseSpec = headersSpec.header(
            HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE
        )
            .accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML)
            .acceptCharset(StandardCharsets.UTF_8)
            .ifNoneMatch("*")
            .ifModifiedSince(ZonedDateTime.now())
            .retrieve()

//        val response = headersSpec.retrieve().bodyToMono(String::class.java)
//        println(response)

        // or
        val response = headersSpec.exchangeToMono { response: ClientResponse ->
            if (response.statusCode() == HttpStatus.OK) {
                return@exchangeToMono response.bodyToMono<String>(String::class.java)
            } else if (response.statusCode().is4xxClientError) {
                return@exchangeToMono Mono.just<String>("Error response")
            } else {
                return@exchangeToMono response.createException()
                    .flatMap<String> { error: WebClientResponseException? ->
                        Mono.error(
                            error!!
                        )
                    }
            }
        }
        println(response)
        println(responseSpec.bodyToMono(String::class.java))


    }

}