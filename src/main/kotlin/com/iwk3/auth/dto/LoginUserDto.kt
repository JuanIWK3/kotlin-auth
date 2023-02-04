package com.iwk3.auth.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
class LoginUserDto (
    val email: String,
    val password: String
)