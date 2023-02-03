package com.iwk3.auth.service

import com.iwk3.auth.model.User
import com.iwk3.auth.repository.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestBody

@Service
class UserService {
    @Autowired
    private lateinit var userRepo: UserRepo

    fun getAll(): List<User> {
        return userRepo.findAll()
    }

    fun create(user: User): User {
        return userRepo.save(user)
    }
}