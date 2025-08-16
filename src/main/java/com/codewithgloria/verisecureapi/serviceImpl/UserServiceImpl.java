package com.codewithgloria.verisecureapi.serviceImpl;

import com.codewithgloria.verisecureapi.dto.LoginDto;
import com.codewithgloria.verisecureapi.exceptions.HandleEventDoesNotExistException;
import com.codewithgloria.verisecureapi.exceptions.HandleInvalidCredentialsException;
import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.repository.UserRepository;
import com.codewithgloria.verisecureapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Users registration(Users user) {
        Users newUser = new Users();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        newUser.setPassword(hashedPassword);

        return userRepository.save(newUser);
    }
    @Override
    public String  login(LoginDto dto) throws HandleInvalidCredentialsException {
        Users existing = userRepository.findByEmailIgnoreCase(dto.getEmail())
                .orElseThrow(() -> new HandleEventDoesNotExistException("User does not exist"));

        if (!passwordEncoder.matches(dto.getPassword(), existing.getPassword())) {
            throw new HandleInvalidCredentialsException("Invalid password");
        }

        return "Login Successful";
    }
}


