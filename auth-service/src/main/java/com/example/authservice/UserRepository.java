package com.example.authservice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository("userRepository")
public interface UserRepository extends JpaRepository<UserCredentials, Long> {

	UserCredentials findByUsername(String username);
}