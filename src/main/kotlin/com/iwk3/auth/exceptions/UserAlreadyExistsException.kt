package com.iwk3.auth.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus


@ResponseStatus(value = HttpStatus.CONFLICT)
class UserAlreadyExistsException(override val message: String) : BaseException(
    message = message,
    status = HttpStatus.CONFLICT
)
