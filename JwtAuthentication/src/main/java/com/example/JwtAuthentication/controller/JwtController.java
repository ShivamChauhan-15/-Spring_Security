package com.example.JwtAuthentication.controller;

import com.example.JwtAuthentication.helper.JwtUtils;
import com.example.JwtAuthentication.model.JwtResponse;
import com.example.JwtAuthentication.model.UserModel;
import com.example.JwtAuthentication.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationManagerResolver;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Controller
public class JwtController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetails customUserDetails;
    @Autowired
    private JwtUtils jwtUtils;
    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UserModel userModel, HttpServletResponse response, Model model) throws Exception{
        try {
            this.authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userModel.getUserName(), userModel.getPassword()));
        }
        catch(Exception e){
            e.printStackTrace();
            throw new Exception("Invalid Credentials");
        }

        //after authenticated
        UserDetails userDetails=customUserDetails.loadUserByUsername(userModel.getUserName());

        //data add to be token
        Map<String,Object>dataToAdd=new HashMap<>();
        dataToAdd.put("Name", "Shivam");
        dataToAdd.put("Address", "Sector 62,Noida");
        //now generate token
        String token=this.jwtUtils.generateToken(dataToAdd,userDetails);
        //{"token":value}
        response.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        model.addAttribute("token",token);
//        return "home";
        return  ResponseEntity.ok().body(new JwtResponse(token));

    }
}
