package com.iwk3.auth.controllers

import com.iwk3.auth.exceptions.UserAlreadyExistsException
import com.iwk3.auth.model.User
import com.iwk3.auth.service.UserService
import com.iwk3.auth.services.AuthService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthController {
    @Autowired
    private lateinit var authService: AuthService

    @PostMapping("/register")
    fun create(@RequestBody user: User): ResponseEntity<Any> {
        return try {
            ResponseEntity.ok().body(authService.register(user))
        } catch (ex: UserAlreadyExistsException) {
            ex.res()
        }
    }
}