package com.codewithgloria.verisecureapi.serviceImpl;

import com.codewithgloria.verisecureapi.dto.LoginDto;
import com.codewithgloria.verisecureapi.enums.Role;
import com.codewithgloria.verisecureapi.exceptions.HandleEventDoesNotExistException;
import com.codewithgloria.verisecureapi.exceptions.HandleInvalidCredentialsException;
import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.repository.UserRepository;
import com.codewithgloria.verisecureapi.service.EmailService;
import com.codewithgloria.verisecureapi.service.JwtService;
import com.codewithgloria.verisecureapi.service.OtpService;
import com.codewithgloria.verisecureapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final OtpService otpService;
    private final JwtService jwtService;

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

        if (user.getRole() == null) {
            newUser.setRole(Role.ROLE_USER);
        } else {
            if (user.getRole() == Role.ROLE_ADMIN) {
                throw new HandleInvalidCredentialsException("Cannot self-register as admin");
            }
            newUser.setRole(user.getRole());
        }

        Users savedUser = userRepository.save(newUser);

        String otp = otpService.generateOtp(savedUser.getEmail());
        emailService.sendOtp(savedUser.getEmail(), otp);

        return savedUser;
    }

    @Override
    public String login(LoginDto dto) throws HandleInvalidCredentialsException {
        Users existing = userRepository.findByEmailIgnoreCase(dto.getEmail())
                .orElseThrow(() -> new HandleEventDoesNotExistException("User does not exist"));

        if (!passwordEncoder.matches(dto.getPassword(), existing.getPassword())) {
            throw new HandleInvalidCredentialsException("Invalid password");
        }

        if (!existing.isVerified()) {
            throw new HandleInvalidCredentialsException("Please verify your email first.");
        }

        return jwtService.generateToken(existing);
    }

    @Override
    public boolean verifyOtp(String email, String otp) {
        boolean isValid = otpService.validateOtp(email, otp);

        if (isValid) {
            Users user = userRepository.findByEmailIgnoreCase(email)
                    .orElseThrow(() -> new HandleEventDoesNotExistException("User does not exist"));
            user.setVerified(true);
            userRepository.save(user);
            return true;
        }
        return false;
    }
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}
