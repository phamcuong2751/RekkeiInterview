package com.practicejava.demo.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    @GetMapping("")
    public ResponseEntity<?> user() {
        String message = "Hello user's pages";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
