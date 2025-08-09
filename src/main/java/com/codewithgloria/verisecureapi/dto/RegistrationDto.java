package com.codewithgloria.verisecureapi.dto;


import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class RegistrationDto {
    private String FirstName;
    private String LastName;

    private String Email;
    private String Password;



}
