package com.neighborhood.connect.jwtlib.entities

import jakarta.persistence.*

@Entity
@Table(name = "user_credentials")
data class JWTUserCredentials (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,

    @Column(name = "username", nullable = false)
    val username: String?,

    @Column(name = "password", nullable = false)
    val password: String?
) {
    constructor() : this(null, null, null)
}
