package com.codewithgloria.verisecureapi.controller;


import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/verisecure")
public class UserController {

    private UserService userService;

    @PostMapping("/registration")
    public Users registration(@RequestBody Users user) {
        userService.registration(user);
        return user;
    }
}


