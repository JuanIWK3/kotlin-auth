package com.iwk3.auth.model

import com.iwk3.auth.dto.UserResponseDto
import jakarta.persistence.*
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.List


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "_users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long?,
    @Column
    var name: String,
    @Column
    var email: String,
    @Column
    var pw: String,
    @Enumerated
    var role: Role

) : UserDetails {
    fun toDto(): UserResponseDto {
        return UserResponseDto(id = this.id!!, username = this.name, email = this.email)
    }

    override fun getAuthorities(): Collection<GrantedAuthority?> {
        return listOf(SimpleGrantedAuthority(role.name))
    }

    override fun getPassword(): String {
        return this.pw
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}
