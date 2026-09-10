package com.Easton.motordesk_backend.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@AllArgsConstructor

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final AppUserDetailsService appUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;

        //Check if header contains Bearer token
        //returns if not valid, else continues
        if(authHeader == null || !authHeader.startsWith("Bearer ")){

            //run request through to prevent it hanging
            filterChain.doFilter(request, response);
            return;
        }

        //extract token, minus the "Bearer"
        jwt = authHeader.substring(7);

        //extract username from token
        userEmail = jwtUtil.extractUsername(jwt);

        //get userDetails
        UserDetails userDetails = appUserDetailsService.loadUserByUsername(userEmail);

        //validate token against user
        if(jwtUtil.validateToken(jwt, userEmail)) {
            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(
                            userDetails,
                    null,
                    userDetails.getAuthorities()
            );

        //update security to know token and user are validated/authorized
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request,response); //pass request past checkpoint
    }
}
