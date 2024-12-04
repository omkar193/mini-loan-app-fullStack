package com.techdome.miniLoanApp.TestControllers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.techdome.miniLoanApp.controller.UserController;
import com.techdome.miniLoanApp.dto.LoginResponse;
import com.techdome.miniLoanApp.model.User;
import com.techdome.miniLoanApp.service.AuthenticationService;
import com.techdome.miniLoanApp.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

    @Mock
    private UserService userService;

    @Mock
    private AuthenticationService authenticationService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserController userController;

    @Test
    void testRegisterUser_Success() {
        User user = new User();
        user.setUsername("testUser");
        user.setPassword("testPassword");

        User savedUser = new User();
        savedUser.setId("userId123");
        savedUser.setUsername("testUser");
        savedUser.setRole("ROLE_CUSTOMER");

        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userService.registerUser(any(User.class))).thenReturn(savedUser);

        ResponseEntity<?> response = userController.registerUser(user);

        assertNotNull(response);
        assertEquals(savedUser, response.getBody());
        verify(passwordEncoder, times(1)).encode("testPassword");
        verify(userService, times(1)).registerUser(any(User.class));
    }

    @Test
    void testRegisterUser_MissingUsernameOrPassword() {
        User user = new User();
        user.setUsername(null);
        user.setPassword("testPassword");

        ResponseEntity<?> response = userController.registerUser(user);

        assertNotNull(response);
        assertEquals(400, response.getStatusCodeValue());
        assertEquals("Username and Password must not be empty.", response.getBody());
        verify(userService, never()).registerUser(any(User.class));
    }

    @Test
    void testLogin_Success() {
        User loginRequest = new User();
        loginRequest.setUsername("testUser");
        loginRequest.setPassword("testPassword");

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("sampleJwtToken");
        loginResponse.setUser(loginRequest);

        when(authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword())).thenReturn(loginResponse);

        ResponseEntity<LoginResponse> response = userController.login(loginRequest);

        assertNotNull(response);
        assertEquals(loginResponse, response.getBody());
        verify(authenticationService, times(1)).login("testUser", "testPassword");
    }

    @Test
    void testLogin_InvalidCredentials() {
        User loginRequest = new User();
        loginRequest.setUsername("invalidUser");
        loginRequest.setPassword("wrongPassword");

        when(authenticationService.login(loginRequest.getUsername(), loginRequest.getPassword()))
                .thenThrow(new IllegalArgumentException("Invalid username or password"));

        try {
            userController.login(loginRequest);
        } catch (Exception e) {
            assertEquals("Invalid username or password", e.getMessage());
        }

        verify(authenticationService, times(1)).login("invalidUser", "wrongPassword");
    }
}

