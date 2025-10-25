package com.user_service.user.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user_service.user.DTO.AuthResponse;
import com.user_service.user.DTO.LoginRequest;
import com.user_service.user.DTO.RegisterRequest;
import com.user_service.user.User.User;
import com.user_service.user.jwtutil.JwtUtil;
import com.user_service.user.service.UserService;

@RestController
@RequestMapping("/api/auth")
public class UserController {

	@Autowired
    private UserService userService;
    
    @Autowired
    private JwtUtil jwtUtil;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
    
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request){
    	return ResponseEntity.ok(userService.register(request));
    }
    
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request){
    	return ResponseEntity.ok(userService.login(request));
    }
  
    
   
}
