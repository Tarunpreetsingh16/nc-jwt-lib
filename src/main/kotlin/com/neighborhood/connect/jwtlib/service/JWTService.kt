package com.neighborhood.connect.jwtlib.service

import com.neighborhood.connect.jwtlib.model.CustomUserDetails
import com.neighborhood.connect.jwtlib.service.db.JWTUserCredentialsRepositoryServiceImpl
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class JWTService(private val userCredentialsRepositoryService: JWTUserCredentialsRepositoryServiceImpl): UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails {
        val userCredentials = userCredentialsRepositoryService.doesUserExist(username)
            ?: throw UsernameNotFoundException("User not found with username: $username ")
        return CustomUserDetails(userCredentials)
    }
}