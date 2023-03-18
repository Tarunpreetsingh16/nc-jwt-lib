package com.neighborhood.connect.jwtlib.util

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.Date
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec
import kotlin.Exception
import kotlin.jvm.Throws

@Component
class JWTTokenUtil(@Value("\${jwt.secret}") secret :String): Serializable {



    private val SECRET_KEY: SecretKey = SecretKeySpec(secret.toByteArray(), SignatureAlgorithm.HS256.jcaName)
    private val JWT_TOKEN_VALIDITY = (5 * 60 * 60).toLong()

    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token) { obj: Claims -> obj.subject }
    }
//
    private fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token) { obj: Claims -> obj.expiration }
    }
//
    private fun <Any> getClaimFromToken(token: String, claimsResolver: (Claims) -> Any): Any {
        val claims: Claims = getAllClaimsFromToken(token)
        return claimsResolver(claims)
    }
//
    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parserBuilder().setSigningKey(SECRET_KEY).build().parseClaimsJws(token).body
    }
//
//    //check if the token has expired
    private fun isTokenExpired(token: String): Boolean {
        val expiration: Date =  getExpirationDateFromToken(token)
        return expiration.before(Date())
    }
//
    //generate token for user
    @Throws(Exception::class)
    fun generateToken(userDetails: UserDetails): String {
        val claims = HashMap<String, Any>();
        return doGenerateToken(claims, userDetails.username);
    }

    @Throws(Exception::class)
    fun doGenerateToken(claims: HashMap<String, Any> , subject: String): String {
            return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(Date(System.currentTimeMillis()))
                .setExpiration(Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SECRET_KEY).compact()
    }

    //validate token
    public fun validateToken(token: String, userDetails: UserDetails): Boolean {
        val username: String = getUsernameFromToken(token);
        return (username == userDetails.username && !isTokenExpired(token));
    }
}
