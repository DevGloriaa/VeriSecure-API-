package com.codewithgloria.verisecureapi.model;

//import jakarta.persistence.*;
import com.codewithgloria.verisecureapi.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "VeriSecure")
@Getter
@Setter
public class Users {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;

    @NotBlank (message = "Cannot be blank")
    private String firstName;
    @NotBlank (message = "Cannot be blank")
    private String lastName;
    private String phoneNumber;

//    @Column(unique = true)
    @Email
    private String email;
    private String gender;

    @NotBlank (message = "password cannot be left blank")
    @Size(min = 3, max = 20)
    private String password;

    private boolean verified =  false;
    private Role role;

}
