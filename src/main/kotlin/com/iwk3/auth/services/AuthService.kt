package com.iwk3.auth.services

import com.iwk3.auth.dto.UserResponseDto
import com.iwk3.auth.exceptions.UserAlreadyExistsException
import com.iwk3.auth.model.User
import com.iwk3.auth.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthService {
    @Autowired
    private lateinit var userRepo: UserRepo

    fun register(user: User): UserResponseDto {
        var alreadyExists = userRepo.findByEmail(user.email)

        if (alreadyExists.isPresent) {
            throw UserAlreadyExistsException("User with email = '${user.email}' already exists")
        }

        user.password = BCryptPasswordEncoder().encode(user.password)
        userRepo.save(user)

        return user.toDto()
    }

    fun login(user: User): User {
        return userRepo.save(user)
    }
}