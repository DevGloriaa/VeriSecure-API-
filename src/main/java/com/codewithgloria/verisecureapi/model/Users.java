package com.codewithgloria.verisecureapi.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userid;

    @NotBlank (message = "Cannot be blank")
    private String FirstName;
    @NotBlank (message = "Cannot be blank")
    private String LastName;
    private String PhoneNumber;

    @Column(unique = true)
    @Email
    private String Email;
    private String Gender;

    @NotBlank (message = "password cannot be left blank")
    @Size(min = 3, max = 20)
    private String password;

}
