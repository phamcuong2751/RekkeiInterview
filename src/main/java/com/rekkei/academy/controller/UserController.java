package com.rekkei.academy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/welcome")
public class UserController {

    @GetMapping("")
    public ResponseEntity<?> welcome() {
        String message = "Welcome to my API! Start exploring and make amazing things happen.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
