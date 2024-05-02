package com.example.JwtAuthentication.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @GetMapping("/home")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_USER')")
//    @Secured({"ROLE_ADMIN"})
    public String home(){
        System.out.println("in home controller");
        return "Welcome!!";
    }
    @GetMapping("/form")
    public String login(){
        return "loginPage";
    }
}
