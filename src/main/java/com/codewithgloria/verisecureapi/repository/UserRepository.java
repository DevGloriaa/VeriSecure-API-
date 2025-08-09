package com.codewithgloria.verisecureapi.repository;

import com.codewithgloria.verisecureapi.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<Users, String> {
}
