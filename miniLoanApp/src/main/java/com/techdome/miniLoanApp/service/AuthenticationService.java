package com.techdome.miniLoanApp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import com.techdome.miniLoanApp.dto.LoginResponse;
import com.techdome.miniLoanApp.model.User;
import com.techdome.miniLoanApp.repository.UserRepository;

@Service
public class AuthenticationService {
	
	
	@Autowired
    private JwtService jwtService;
	
	 @Autowired
	    private UserRepository userRepository;
	 
	    @Autowired
	    private AuthenticationManager authenticationManager;

	   public LoginResponse login(String username, String password) {
	        
	        authenticationManager.authenticate(
	                new UsernamePasswordAuthenticationToken(username, password)
	        );

	        User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

	        String jwt = jwtService.generateToken(user);

	        return new LoginResponse(jwt, user);
	    }
	   
	   
}
