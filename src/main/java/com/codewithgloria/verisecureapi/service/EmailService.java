package com.codewithgloria.verisecureapi.service;

public interface EmailService {
    void sendOtp(String email, String otp);
}
