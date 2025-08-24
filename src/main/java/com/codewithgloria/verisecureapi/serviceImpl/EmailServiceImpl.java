package com.codewithgloria.verisecureapi.serviceImpl;

import com.codewithgloria.verisecureapi.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {


    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendOtp(String email, String otp) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("obiorahgloria911@gmail.com");
        message.setTo(email);
        message.setSubject("your OTP Verification Code");
        message.setText("Your OTP code is: "+ otp + "/n This code expires in 10 minutes.");

        mailSender.send(message);
    }
}
