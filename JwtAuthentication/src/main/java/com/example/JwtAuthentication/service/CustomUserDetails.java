package com.example.JwtAuthentication.service;

import com.example.JwtAuthentication.model.UserModel;
import com.example.JwtAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;
@Service
public class CustomUserDetails implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<UserModel> byUserName = userRepository.findByUserName(userName);
        if(byUserName.isPresent()){
            UserModel userModel = byUserName.get();
            GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_USER");  //setting role
            return new User(userModel.getUserName(),userModel.getPassword(), Arrays.asList(authority));
        }
        else
            throw new UsernameNotFoundException("user not exist");
    }
}
