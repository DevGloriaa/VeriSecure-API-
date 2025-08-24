package com.codewithgloria.verisecureapi.controller;


import com.codewithgloria.verisecureapi.dto.LoginDto;
import com.codewithgloria.verisecureapi.model.OtpVerification;
import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.repository.OtpRepository;
import com.codewithgloria.verisecureapi.repository.UserRepository;
import com.codewithgloria.verisecureapi.service.EmailService;
import com.codewithgloria.verisecureapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/verisecure")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private OtpRepository otpRepository;

    @PostMapping("/registration")
    public String registration(@RequestBody Users user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);

        String otp = String.valueOf((int) (Math.random() * 900000)+100000);
        OtpVerification verification = new OtpVerification();
        verification.setEmail(user.getEmail());
        verification.setOtp(otp);
        verification.setExpiresAt(LocalDateTime.now().plusMinutes(10));
        otpRepository.save(verification);

        emailService.sendOtp(user.getEmail(), otp);
        return "OTP sent to email";
    }
    @PostMapping("/verify-email")
    public ResponseEntity<String> verifyEmail(@RequestBody Map<String, String> payload) {
        String email = payload.get("email");
        String otp = payload.get("otp");

        if (email == null || otp == null) {
            return ResponseEntity.badRequest().body("Email and OTP are required");
        }

        OtpVerification record = otpRepository.findByEmailAndOtp(email, otp)
                .orElse(null);

        if (record == null) {
            return ResponseEntity.status(400).body("Invalid OTP");
        }

        if (record.getExpiresAt().isBefore(LocalDateTime.now())) {
            return ResponseEntity.status(400).body("OTP expired");
        }

        Users user = userRepository.findByEmailIgnoreCase(email)
                .orElse(null);

        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        user.setVerified(true);
        userRepository.save(user);

        return ResponseEntity.ok("Email verified successfully");
    }


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto user) {
        String token = userService.login(user);
        return ResponseEntity.ok(token);
    }



}


