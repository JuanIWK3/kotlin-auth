package com.iwk3.auth.services

import com.iwk3.auth.dto.AuthResponseDto
import com.iwk3.auth.dto.LoginUserDto
import com.iwk3.auth.dto.RegisterUserDto
import com.iwk3.auth.exceptions.InvalidCredentialsException
import com.iwk3.auth.exceptions.UserAlreadyExistsException
import com.iwk3.auth.exceptions.UserNotFoundException
import com.iwk3.auth.model.Role
import com.iwk3.auth.model.User
import com.iwk3.auth.repositories.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.util.*


@Service
class AuthService {
    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var jwtService: JwtService

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    fun register(registerDto: RegisterUserDto): AuthResponseDto {
        var alreadyExists = userRepo.findByEmail(registerDto.email)

        if (alreadyExists.isPresent) {
            throw UserAlreadyExistsException("User with email = '${registerDto.email}' already exists")
        }

        val user = User(
            id = 0,
            name = registerDto.username,
            email = registerDto.email,
            pw = passwordEncoder.encode(registerDto.password),
            role = Role.USER
        )

        userRepo.save(user)

        return AuthResponseDto(token = jwtService.generateToken(user), user = user.toDto())
    }

    fun login(userDto: LoginUserDto): AuthResponseDto {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                userDto.email,
                userDto.password
            )
        )

        val user = userRepo.findByEmail(userDto.email).orElseThrow {
            UserNotFoundException("User with email = '${userDto.email}' not found!")
        }

        val jwtToken = jwtService.generateToken(user)

        if (!passwordEncoder.matches(userDto.password, user.pw)) {
            throw InvalidCredentialsException()
        }

        return AuthResponseDto(token = jwtToken , user = user.toDto())
    }
}