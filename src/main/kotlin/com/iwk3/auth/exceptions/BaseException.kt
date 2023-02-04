package com.iwk3.auth.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

open class BaseException(override val message: String, private val status: HttpStatus) : RuntimeException(message) {
    fun res(): ResponseEntity<Any> {
        return ResponseEntity.status(this.status)
            .body(ErrorResponse(status = this.status.value(), message = this.message))
    }
}