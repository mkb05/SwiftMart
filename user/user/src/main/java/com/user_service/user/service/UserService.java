package com.user_service.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.user_service.user.DTO.AuthResponse;
import com.user_service.user.DTO.LoginRequest;
import com.user_service.user.DTO.RegisterRequest;
import com.user_service.user.User.User;
import com.user_service.user.jwtutil.JwtUtil;
import com.user_service.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	private final JwtUtil jwtUtil;
	
	private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

	public UserService(UserRepository userRepository, JwtUtil jwtUtil) {
		super();
		this.userRepository = userRepository;
		this.jwtUtil = jwtUtil;
	}
	
	
	public AuthResponse register(RegisterRequest request) {
		if(userRepository.existsByEmail(request.getEmail())) {
			throw new RuntimeException("Email already exists");
		}
		
		User user=new User();
		user.setEmail(request.getEmail());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		
		userRepository.save(user);
		
		String token=jwtUtil.generateToken(user.getEmail());
		return new AuthResponse("User registered successfully",token);
		
	}
	
	
	public AuthResponse login(LoginRequest request) {
		User user=userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("Invalid email or password"));
		
		if(!passwordEncoder.matches(request.getPassword(),user.getPassword())) {
			throw new RuntimeException("Invalid email or password");
		}
		
		String token=jwtUtil.generateToken(user.getEmail());
		
		return new AuthResponse("Login successful",token);
	}

    
    

	
}
