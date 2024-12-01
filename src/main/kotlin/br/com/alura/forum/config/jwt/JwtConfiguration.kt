package br.com.alura.forum.config.jwt

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtConfiguration {

    private val expiration: Long = 60000;

    @Value("\${jwt.secret}")
    private lateinit var secret: String;

    fun generateToken(userName: String?): String? {
        val key = Keys.hmacShaKeyFor(secret.toByteArray());

        val exp = Date(System.currentTimeMillis() + expiration);

        return Jwts.builder()
            .setSubject(userName)
            .setExpiration(exp)
            .setIssuedAt(Date())
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    fun isValid(jwt: String?): Boolean {
        return try {
            Jwts.parserBuilder()
                .setSigningKey(secret.toByteArray())
                .build()
                .parseClaimsJws(jwt)
            true
        } catch (e: IllegalArgumentException) {
            false
        }
    }

    fun getAuthentication(jwt: String?): Authentication {
        val userName = Jwts.parserBuilder()
            .setSigningKey(secret.toByteArray())
            .build()
            .parseClaimsJws(jwt)
            .body
            .subject;

        return UsernamePasswordAuthenticationToken(userName, null, null)
    }
}