package com.codewithgloria.verisecureapi.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
@Data
@RequiredArgsConstructor
@Document(collection = "otp")
public class OtpVerification {
    @Id

    private String id;
    private String email;
    private String otp;
    private LocalDateTime expiresAt;

}
