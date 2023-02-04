package com.iwk3.auth.model

import com.iwk3.auth.dto.UserResponseDto
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_users")
class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    var username: String,
    var email: String,
    var password: String
) {

    fun toDto(): UserResponseDto {
        return UserResponseDto(id = this.id!!, username = this.username, email = this.email)
    }
}
