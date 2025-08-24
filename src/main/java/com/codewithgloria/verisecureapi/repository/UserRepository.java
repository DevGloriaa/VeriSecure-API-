package com.codewithgloria.verisecureapi.repository;

import com.codewithgloria.verisecureapi.model.Users;
//import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<Users, String> {
    Optional<Users> findByEmailIgnoreCase(String email);
}