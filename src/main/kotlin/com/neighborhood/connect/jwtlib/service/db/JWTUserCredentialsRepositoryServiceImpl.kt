package com.neighborhood.connect.jwtlib.service.db

import com.neighborhood.connect.jwtlib.repositories.IJWTUserCredentialsRepository
import com.neighborhood.connect.jwtlib.entities.JWTUserCredentials
import org.springframework.stereotype.Service

@Service
class JWTUserCredentialsRepositoryServiceImpl(private val userCredentialsRepository: IJWTUserCredentialsRepository)
{
    fun doesUserExist(username: String): JWTUserCredentials? {
        return userCredentialsRepository.findByUsername(username)
    }
}