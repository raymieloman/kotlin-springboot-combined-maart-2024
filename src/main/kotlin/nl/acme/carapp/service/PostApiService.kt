package nl.acme.carapp.service

import nl.acme.carapp.model.PostDTO
import org.springframework.stereotype.Service
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

@Service
class PostApiService(private val webClient: WebClient) {

    suspend fun findAllPosts(): List<PostDTO> =
        webClient.get().uri("/posts")
            .retrieve().awaitBody<List<PostDTO>>()
}