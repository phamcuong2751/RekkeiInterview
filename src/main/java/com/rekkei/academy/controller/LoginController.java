package com.rekkei.academy.controller;

import com.rekkei.academy.entities.UserEntity;
import com.rekkei.academy.payload.BaseResponse;
import com.rekkei.academy.service.LoginService;
import com.rekkei.academy.utils.JWTUtilsHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class LoginController {
    private final AuthenticationManager authenticationManager;
    private final JWTUtilsHelper jwtUtilsHelper;
    private final LoginService loginService;
    public LoginController(LoginService loginService, JWTUtilsHelper jwtUtilsHelper, AuthenticationManager authenticationManager) {
        this.loginService = loginService;
        this.jwtUtilsHelper = jwtUtilsHelper;
        this.authenticationManager = authenticationManager;
    }

    /**
     * @solution
     * Using AuthenticationManager and execute authenticate()
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody UserEntity user
    ) {
        String email = user.getEmail();
        String password  = user.getPassword();
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );
        String jwt = jwtUtilsHelper.generateToken(email);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        BaseResponse response = new BaseResponse();
        response.setMessage("Login Success");
        response.setData("token: " + jwt);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UserEntity user){
        Integer isSuccess = loginService.userRegistration(user);
        BaseResponse response = new BaseResponse();
        response.setMessage(isSuccess == 1 ? "Register successful!" : "Registration failed");
        response.setData(isSuccess);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
