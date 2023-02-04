package com.iwk3.auth.repositories

import com.iwk3.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepo : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}
