package com.techdome.miniLoanApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techdome.miniLoanApp.dto.LoginResponse;
import com.techdome.miniLoanApp.model.User;
import com.techdome.miniLoanApp.service.AuthenticationService;
import com.techdome.miniLoanApp.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {

	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private AuthenticationService authenticationService;
	    
	    @Autowired
	    private PasswordEncoder passwordEncoder;
	    
	    @PostMapping("/register")
	    public ResponseEntity<?> registerUser(@RequestBody User user) {
	        if (user.getUsername() == null || user.getPassword() == null) {
	            return ResponseEntity.badRequest().body("Username and Password must not be empty.");
	        }
	    	user.setRole(User.ROLE_CUSTOMER);
	    	user.setPassword(passwordEncoder.encode(user.getPassword()));
	        User savedUser = userService.registerUser(user);
	        return ResponseEntity.ok(savedUser);
	    }
	    
	    
	    @PostMapping("/login")
	    public ResponseEntity<LoginResponse> login(@RequestBody User loginRequest) {
	      
	        LoginResponse response = authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword());
	        return ResponseEntity.ok(response);
	    }
}
