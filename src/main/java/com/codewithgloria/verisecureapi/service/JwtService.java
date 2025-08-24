package com.codewithgloria.verisecureapi.service;

import com.codewithgloria.verisecureapi.model.Users;

public interface JwtService {
    public String generateToken(Users user);
}
