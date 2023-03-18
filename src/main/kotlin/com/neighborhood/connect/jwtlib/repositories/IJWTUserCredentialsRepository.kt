package com.neighborhood.connect.jwtlib.repositories

import com.neighborhood.connect.jwtlib.entities.JWTUserCredentials
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IJWTUserCredentialsRepository : JpaRepository<JWTUserCredentials, Long> {
    fun findByUsername(username: String): JWTUserCredentials?
}