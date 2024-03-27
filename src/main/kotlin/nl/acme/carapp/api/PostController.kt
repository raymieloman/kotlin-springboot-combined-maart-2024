package nl.acme.carapp.api

import nl.acme.carapp.service.PostApiService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/posts")
class PostController(private val service: PostApiService) {

    @GetMapping
    suspend fun findAllPosts() = service.findAllPosts()
}