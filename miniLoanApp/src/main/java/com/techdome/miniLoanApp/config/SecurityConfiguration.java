package com.techdome.miniLoanApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.techdome.miniLoanApp.service.JwtService;
import com.techdome.miniLoanApp.service.UserService;


@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private JwtAuthenticationFilter jwtAuthenticationFilter;    
    private UserService userService;    
	private JwtService jwtService;    
    

    public SecurityConfiguration() {
		super(); 
	}
    
    @Autowired
	public SecurityConfiguration(JwtAuthenticationFilter jwtAuthenticationFilter, UserService userService,
			JwtService jwtService) {
		super();
		this.jwtAuthenticationFilter = jwtAuthenticationFilter;
		this.userService = userService;
		this.jwtService = jwtService;
	}
    
 
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http.csrf(AbstractHttpConfigurer::disable)
	        .authorizeHttpRequests(request -> request
	            .requestMatchers("/api/**").permitAll()
//	            .requestMatchers("/api/repayments/**").permitAll()	            	            
//	            .requestMatchers("/api/user/**").hasAnyAuthority("ROLE_CUSTOMER","ROLE_ADMIN") 
	            .anyRequest().authenticated() 
	        )
	        .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
	        .authenticationProvider(authenticationProvider())
	        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	    
	    return http.build();
	}
		 
    @Bean
    public AuthenticationProvider authenticationProvider() {
    	DaoAuthenticationProvider authenticationProvider= new DaoAuthenticationProvider();
    	authenticationProvider.setUserDetailsService(userService.userDetailsService());
    	authenticationProvider.setPasswordEncoder(passwordEncoder());
    	return authenticationProvider;
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
    	return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration config )  throws Exception {
    	return config.getAuthenticationManager();
    }
}
