package com.example.GoogleOAuthSignin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {
    @RequestMapping(value = "/user")
    public Principal user(Principal principal) {
        return principal;
    }
}
