package com.iwk3.auth.controllers

import com.iwk3.auth.model.User
import com.iwk3.auth.service.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController {
    @Autowired
    private lateinit var userService: UserService

    @GetMapping("/all")
    fun getAll(): ResponseEntity<List<User>> {
        return ResponseEntity.ok().body(userService.getAll())
    }
}