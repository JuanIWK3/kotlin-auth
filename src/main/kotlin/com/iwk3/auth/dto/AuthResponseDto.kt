package com.iwk3.auth.dto

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@NoArgsConstructor
@AllArgsConstructor
class AuthResponseDto (
    token: String,
    user: UserResponseDto
)