package nl.acme.carapp.api

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MissingPathVariableException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler


@ControllerAdvice
class RestResponseEntityExceptionHandler: ResponseEntityExceptionHandler() {

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

    @ExceptionHandler(value = [NullPointerException::class])
    protected fun handleExceptionNPE(
        ex: java.lang.NullPointerException?, request: WebRequest?
    ): ResponseEntity<Any?>? {
        val bodyOfResponse = "This entity does not exist!"
        return handleExceptionInternal(
            ex!!, bodyOfResponse,
            HttpHeaders(), HttpStatus.CONFLICT, request!!
        )
    }

    @ExceptionHandler(value = [MissingPathVariableException::class])
    protected fun handleExceptionRunTime(
        ex: MissingPathVariableException?, request: WebRequest?
    ): ResponseEntity<Any?>? {
        val bodyOfResponse = "This entity does not exist!"
        return handleExceptionInternal(
            ex!!, bodyOfResponse,
            HttpHeaders(), HttpStatus.CONFLICT, request!!
        )
    }
}

