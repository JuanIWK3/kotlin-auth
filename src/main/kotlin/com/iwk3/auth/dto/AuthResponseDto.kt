package com.iwk3.auth.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
data class AuthResponseDto (
    val token: String,
    val user: UserResponseDto
)