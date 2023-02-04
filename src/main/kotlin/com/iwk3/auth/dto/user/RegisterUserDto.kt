package com.iwk3.auth.dto.user

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class RegisterUserDto (
    val username: String,
    val email: String,
    val password: String
)