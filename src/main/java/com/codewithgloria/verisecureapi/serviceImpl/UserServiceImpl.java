package com.codewithgloria.verisecureapi.serviceImpl;

import com.codewithgloria.verisecureapi.model.Users;
import com.codewithgloria.verisecureapi.repository.UserRepository;
import com.codewithgloria.verisecureapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;


    @Override
    public Users registration(Users user) {
        Users user1 = new Users();
        user1.setFirstName(user.getFirstName());
        user1.setLastName(user.getLastName());
        user1.setPhoneNumber(user.getPhoneNumber());
        user1.setEmail(user.getEmail());
        user1.setGender(user.getGender());
        user1.setPassword(user.getPassword());

        userRepository.save(user1);
        return user1;
    }
}

