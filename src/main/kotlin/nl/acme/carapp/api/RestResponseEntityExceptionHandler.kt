package nl.acme.carapp.api

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

    // This introduces a new method
    // The exception type is NOT ALLOWED to be in the parent class his method: handleException in the @ExceptionHandler list
    @ExceptionHandler(value = [IllegalArgumentException::class, IllegalStateException::class])
    protected fun handleConflict(
        ex: RuntimeException?, request: WebRequest?
    ): ResponseEntity<Any?>? {
        val bodyOfResponse = "This should be application specific"
        return handleExceptionInternal(
            ex!!, bodyOfResponse,
            HttpHeaders(), HttpStatus.CONFLICT, request!!
        )
    }

    // This also introduces a new method
    @ExceptionHandler(value = [NullPointerException::class])
    protected fun handleExceptionNPE(
        ex: java.lang.NullPointerException?, request: WebRequest?
    ): ResponseEntity<Any?>? {
        val bodyOfResponse = "A NPE occurred, and that even in Kotlin!"
        return handleExceptionInternal(
            ex!!, bodyOfResponse,
            HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request!!
        )
    }

    // This overrides the base method, look carefully how it is invoked in the parent class.
    override fun handleMissingPathVariable(
        ex: MissingPathVariableException,
        headers: HttpHeaders,
        status: HttpStatusCode,
        request: WebRequest
    ): ResponseEntity<Any>? {
        println("Ik zit in de child class")

        // return a custom one
        return handleExceptionInternal(
            ex, "The id is missing",
            HttpHeaders(), HttpStatus.NOT_FOUND, request
        )
    }
}

