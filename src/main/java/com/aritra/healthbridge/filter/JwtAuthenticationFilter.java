package com.aritra.healthbridge.filter;

import com.aritra.healthbridge.service.CustomUserDetailsService;
import com.aritra.healthbridge.util.JwtUtil;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        // Step 1: Read the Authorization header
        final String authHeader = request.getHeader("Authorization");

        // Step 2: No header or doesn't start with "Bearer " → skip, pass through
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Step 3: Extract the token (strip "Bearer " prefix — 7 characters)
        final String token = authHeader.substring(7);

        // Step 4: Extract username from token (signature verified inside JwtUtil)
        final String username = jwtUtil.extractUsername(token);

        // Step 5: Only proceed if username found AND no existing authentication
        // (don't re-authenticate an already authenticated request)
        if (username != null &&
                SecurityContextHolder.getContext().getAuthentication() == null) {

            // Step 6: Load the full UserDetails from DB
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Step 7: Validate token against the loaded user
            if (jwtUtil.isTokenValid(token, userDetails.getUsername())) {

                // Step 8: THE KEY STEP — create auth token and set in SecurityContext
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(
                                userDetails,
                                null,              // credentials null — we don't need password here
                                userDetails.getAuthorities()  // roles
                        );
                // Attach request details (IP, session) to the auth token
                authToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                // Set in SecurityContextHolder — from this point, Spring knows who this is
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }

        // Step 9: Always continue the filter chain — let Spring Security make the final call
        filterChain.doFilter(request, response);
    }
}