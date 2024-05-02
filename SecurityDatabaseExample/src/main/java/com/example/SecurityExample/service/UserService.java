package com.example.SecurityExample.service;

import com.example.SecurityExample.model.UserModel;
import com.example.SecurityExample.repository.UserModelToUserDetails;
import com.example.SecurityExample.repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserService implements UserDetailsService {
    @Autowired
    private UserRespository userRespository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername");
        Optional<UserModel> byUsername = userRespository.findByUsername(userName);
        if(byUsername.isPresent()){
            UserModel userModel = byUsername.get();
            return new UserModelToUserDetails(userModel);
        }
        else
            throw new UsernameNotFoundException("Username not exists");
    }
    public UserModel create(UserModel userModel){
        return userRespository.save(userModel);
    }
}
