package com.iwk3.auth.services

import com.iwk3.auth.dto.AuthResponseDto
import com.iwk3.auth.dto.LoginUserDto
import com.iwk3.auth.dto.RegisterUserDto
import com.iwk3.auth.exceptions.BaseException
import com.iwk3.auth.exceptions.InvalidCredentialsException
import com.iwk3.auth.exceptions.UserAlreadyExistsException
import com.iwk3.auth.exceptions.UserNotFoundException
import com.iwk3.auth.model.User
import com.iwk3.auth.repositories.UserRepo
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.security.Key
import java.util.*

@Service
class AuthService {
    @Autowired
    private lateinit var userRepo: UserRepo

    @Autowired
    private lateinit var passwordEncoder: PasswordEncoder

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    fun register(registerDto: RegisterUserDto): AuthResponseDto {
        var alreadyExists = userRepo.findByEmail(registerDto.email)

        if (alreadyExists.isPresent) {
            throw UserAlreadyExistsException("User with email = '${registerDto.email}' already exists")
        }

        val user = User(
            id = 0,
            username = registerDto.username,
            email = registerDto.email,
            password = passwordEncoder.encode(registerDto.password)
        )

        userRepo.save(user)

        return AuthResponseDto(token = generateToken(user), user = user.toDto())
    }

    fun login(userDto: LoginUserDto): AuthResponseDto {

        val user = userRepo.findByEmail(userDto.email).orElseThrow {
            UserNotFoundException("User with email = '${userDto.email}' not found!")
        }

        val jwtToken = generateToken(user)

        if (!passwordEncoder.matches(userDto.password, user.password)) {
            throw InvalidCredentialsException()
        }

        return AuthResponseDto(token = jwtToken , user = user.toDto())
    }

    private fun getSignInKey(): Key {
        val keyBytes = Decoders.BASE64.decode("7234743777217A25432A462D4A614E645267556B58703273357638782F413F44")
        return Keys.hmacShaKeyFor(keyBytes)
    }

    private fun generateToken(user: User): String {
        return Jwts.builder()
            .setIssuer(user.id.toString())
            .setExpiration(Date(System.currentTimeMillis() + 60 * 24 * 1000)) // 1 Day
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact()
    }
}