package com.codewithgloria.verisecureapi.service;

import com.codewithgloria.verisecureapi.dto.LoginDto;
import com.codewithgloria.verisecureapi.exceptions.HandleInvalidCredentialsException;
import com.codewithgloria.verisecureapi.model.Users;

public interface UserService {
    Users registration (Users user);
    String login (LoginDto user) throws HandleInvalidCredentialsException;
    boolean verifyOtp(String email, String otp);

}
