package com.iwk3.auth.services

import com.iwk3.auth.model.User
import com.iwk3.auth.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired
    private lateinit var userRepo: UserRepo

    fun register(user: User): User {
        return userRepo.save(user)
    }

    fun login(user: User): User {
        return userRepo.save(user)
    }
}