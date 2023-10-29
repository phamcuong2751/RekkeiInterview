package com.practicejava.demo.controllers;

import com.practicejava.demo.utils.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.PasswordAuthentication;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    /**
     * @solution
     * Using AuthenticationManager and execute authenticate()
     */
    @PostMapping("")
    public ResponseEntity<?> login(
            @RequestParam String username,
            @RequestParam String password
    ) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );


        String jwt = jwtTokenProvider.generateToken(username);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //if authentication successful will process next step, it output error

        return new ResponseEntity<>(jwt, HttpStatus.OK);
    }
}
