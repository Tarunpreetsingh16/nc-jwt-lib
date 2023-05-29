package com.neighborhood.connect.jwtlib.model

import com.neighborhood.connect.jwtlib.entities.JWTUserCredentials
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class CustomUserDetails(
    private val userCredentials: JWTUserCredentials
): UserDetails {

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return emptySet()
    }

    override fun getPassword(): String? {
        return userCredentials.password
    }

    override fun getUsername(): String? {
        return userCredentials.username
    }

    fun getUserId(): Int? {
        return userCredentials.id
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