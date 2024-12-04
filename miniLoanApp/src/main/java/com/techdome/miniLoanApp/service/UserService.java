package com.techdome.miniLoanApp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.techdome.miniLoanApp.model.User;
import com.techdome.miniLoanApp.repository.UserRepository;

@Service
public class UserService {

	    @Autowired
	    private UserRepository userRepository;

	    @Autowired
	    private JwtService jwtService;
	    
	    public UserDetailsService userDetailsService() {
			return new UserDetailsService() {

				@Override
				public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {				
					return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
				}
				
			};
		}


	    public User registerUser(User user) {

	          return userRepository.save(user);
	    }
	

	    public Optional<User> findByUsername(String username) {
	        return userRepository.findByUsername(username);
	    }

		public long countUsersByRole(String roleAdmin) {
			return userRepository.countUsersByRole(roleAdmin);
		}
}
