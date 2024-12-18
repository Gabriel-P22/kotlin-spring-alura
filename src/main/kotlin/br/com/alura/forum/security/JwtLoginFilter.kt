package br.com.alura.forum.security

import br.com.alura.forum.config.jwt.JwtConfiguration
import br.com.alura.forum.model.Credentials
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

class JwtLoginFilter(
    private val authManager: AuthenticationManager,
    private val jwtConfiguration: JwtConfiguration
): UsernamePasswordAuthenticationFilter() {

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication {
        val (userName, password) = ObjectMapper().readValue(request?.inputStream, Credentials::class.java);
        val token = UsernamePasswordAuthenticationToken(userName, password);
        return authManager.authenticate(token);
    }

    override fun successfulAuthentication(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        chain: FilterChain?,
        authResult: Authentication?
    ) {
        val user = (authResult?.principal as UserDetails);

        val token = jwtConfiguration.generateToken(user.username, user.authorities);
        response?.addHeader("Authorization", "Bearer $token");
    }

}