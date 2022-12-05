package com.api.v1.onboarding.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            exception.message,
            HttpStatus.NOT_FOUND.value(),
            exception.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.NOT_FOUND)

    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {

        val error = ErrorResponse(
            ex.message,
            HttpStatus.BAD_REQUEST.value(),
            ex.errorCode,
            null
        )

        return ResponseEntity(error, HttpStatus.BAD_REQUEST)

    }


}