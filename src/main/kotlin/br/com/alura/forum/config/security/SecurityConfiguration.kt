package br.com.alura.forum.config.security

import br.com.alura.forum.config.jwt.JwtConfiguration
import br.com.alura.forum.security.JwtAuthenticationFilter
import br.com.alura.forum.security.JwtLoginFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.filter.OncePerRequestFilter

@Configuration
@EnableWebSecurity
class SecurityConfiguration(
    private val jwtConfiguration: JwtConfiguration,
    private val authenticationConfiguration: AuthenticationConfiguration
) {

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { auth ->
                auth.requestMatchers("/h2-console/**").hasAnyAuthority("DB_READ_AND_WRITE")
                auth.requestMatchers(HttpMethod.POST,"/login").permitAll()
                auth.requestMatchers("/topics").hasAnyAuthority("READ_AND_WRITE")
                auth.requestMatchers("/answer").hasAnyAuthority("READ_AND_WRITE")
                auth.anyRequest().authenticated()
            }
            .csrf { it.disable() }
            .headers { headers ->
                headers.frameOptions { it.disable() }
            }
            .sessionManagement { session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            }
            .addFilterBefore(JwtLoginFilter(authManager = authenticationManager(), jwtConfiguration = jwtConfiguration), UsernamePasswordAuthenticationFilter().javaClass)
            .addFilterBefore(JwtAuthenticationFilter(jwtConfiguration = jwtConfiguration), UsernamePasswordAuthenticationFilter().javaClass)

        return http.build()
    }

    @Bean
    fun authenticationProvider(userDetailsService: UserDetailsService, passwordEncoder: PasswordEncoder): AuthenticationProvider {
        val provider = DaoAuthenticationProvider()
        provider.setUserDetailsService(userDetailsService)
        provider.setPasswordEncoder(bCryptPasswordEncoder())
        return provider
    }

    @Bean
    fun authenticationManager(): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }

}