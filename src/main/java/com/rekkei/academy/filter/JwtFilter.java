package com.practicejava.demo.filter;

import com.practicejava.demo.service.CustomUserDetailService;
import com.practicejava.demo.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;


@Component
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String token = getJWTFromRequest(request);
            String username = jwtTokenProvider.getUsernameFromJWT(token);

            System.out.println("doFilterInternal >>>>>>>> " + username);

            if (!username.isEmpty() && jwtTokenProvider.validateToken(token)) {
                SecurityContext context = SecurityContextHolder.getContext();
                UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken("", "", new ArrayList<>());
                context.setAuthentication(user);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        filterChain.doFilter(request, response);
    }

    private String getJWTFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (bearerToken.startsWith("Bearer ")) {
            bearerToken = bearerToken.substring(7);
            System.out.println(bearerToken);
            return bearerToken;
        }

        return "";
    }
}
