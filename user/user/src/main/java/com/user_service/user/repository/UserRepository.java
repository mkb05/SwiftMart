package com.user_service.user.repository;

import java.util.List;
import java.util.Optional;

import javax.management.relation.Role;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user_service.user.User.User;


public interface UserRepository extends JpaRepository<User,Long> {

	
	// Find user by email
    Optional<User> findByEmail(String email);
    
    // Check if email exists
    boolean existsByEmail(String email);
    
   
	
}
