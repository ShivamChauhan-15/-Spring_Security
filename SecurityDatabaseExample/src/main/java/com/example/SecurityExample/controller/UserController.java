package com.example.SecurityExample.controller;

import com.example.SecurityExample.model.UserModel;
import com.example.SecurityExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @GetMapping("/home")
    public String home(){
        return "Welcome to Home!!";
    }
    @PostMapping("/create")
    public ResponseEntity<?> saveUser(@RequestBody UserModel userModel){
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
         return ResponseEntity.ok(userService.create(userModel));
    }
}
