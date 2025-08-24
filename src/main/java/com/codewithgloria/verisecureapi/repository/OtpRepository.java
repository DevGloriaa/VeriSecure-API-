package com.codewithgloria.verisecureapi.repository;

import com.codewithgloria.verisecureapi.model.OtpVerification;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface OtpRepository extends MongoRepository<OtpVerification, String> {
    Optional<OtpVerification> findByEmailAndOtp(String email, String otp);
}
