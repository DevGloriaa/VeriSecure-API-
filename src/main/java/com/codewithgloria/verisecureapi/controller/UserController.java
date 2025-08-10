package com.codewithgloria.verisecureapi.controller;


import com.codewithgloria.verisecureapi.dto.LoginDto;
import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verisecure")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public Users registration(@RequestBody Users user) {
        userService.registration(user);
        return user;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto user) {
        String token = userService.login(user);
        return ResponseEntity.ok(token);
    }

}


