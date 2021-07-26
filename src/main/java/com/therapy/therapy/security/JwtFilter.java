package com.therapy.therapy.security;

import com.therapy.therapy.user.UserDetail;
import com.therapy.therapy.user.UserEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private UserEntityService userEntityService;
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void doFilterInternal(HttpServletRequest servletRequest, HttpServletResponse servletResponse,
                                 FilterChain filterChain)
            throws IOException, ServletException {

        final String authorizationHeader = servletRequest.getHeader("Authorization");
        String userName = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jwt = authorizationHeader.substring(7);
            userName = jwtUtil.extractUserName(jwt);
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetail userDetails =(UserDetail) this.userDetailsService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(jwt, userDetails)) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(servletRequest));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }
}
