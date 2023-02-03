package com.iwk3.auth.repository

import com.iwk3.auth.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepo : JpaRepository<User, Long> {
}