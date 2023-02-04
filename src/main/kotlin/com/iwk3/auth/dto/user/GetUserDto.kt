package com.iwk3.auth.dto.user

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

@Data
@AllArgsConstructor
@NoArgsConstructor
data class GetUserDto (
    var email: String
)