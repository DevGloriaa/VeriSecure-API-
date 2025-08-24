package com.codewithgloria.verisecureapi.serviceImpl;

import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.service.JwtService;

import java.util.Date;

import static javax.crypto.Cipher.SECRET_KEY;

public class JwtServiceImpl implements JwtService {


    public String generateToken(Users user) {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }
}
