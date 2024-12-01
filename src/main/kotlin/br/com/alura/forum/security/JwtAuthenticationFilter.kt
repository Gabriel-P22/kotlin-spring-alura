package br.com.alura.forum.security

import br.com.alura.forum.config.jwt.JwtConfiguration
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JwtAuthenticationFilter(
    private val jwtConfiguration: JwtConfiguration
): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization");
        val jwt = getTokenDetail(token);

        if(jwtConfiguration.isValid(jwt)) {
            val authentication = jwtConfiguration.getAuthentication(jwt);
            SecurityContextHolder.getContext().authentication = authentication;
        }

        filterChain.doFilter(request, response);
    }

    private fun getTokenDetail(token: String?): String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ");
            jwt.substring(7, jwt.length);
        }
    }

}