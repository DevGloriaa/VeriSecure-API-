package com.codewithgloria.verisecureapi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "VeriSecure")
@Getter
@Setter
public class Users {

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
