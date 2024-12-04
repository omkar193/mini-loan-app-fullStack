package com.techdome.miniLoanApp.TestDTO;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import com.techdome.miniLoanApp.dto.LoginResponse;
import com.techdome.miniLoanApp.model.User;

class LoginResponseTest {

    @Test
    void testDefaultConstructor() {
        LoginResponse loginResponse = new LoginResponse();
        assertNotNull(loginResponse);
    }

    @Test
    void testParameterizedConstructor() {
        User user = new User();
        user.setId("userId123");
        user.setUsername("testUser");
        user.setRole("ROLE_CUSTOMER");

        String token = "sampleJwtToken";
        LoginResponse loginResponse = new LoginResponse(token, user);

        assertEquals(token, loginResponse.getToken());
        assertEquals(user, loginResponse.getUser());
    }

    @Test
    void testSetAndGetToken() {
        LoginResponse loginResponse = new LoginResponse();
        String token = "newJwtToken";
        loginResponse.setToken(token);

        assertEquals(token, loginResponse.getToken());
    }

    @Test
    void testSetAndGetUser() {
        LoginResponse loginResponse = new LoginResponse();
        User user = new User();
        user.setId("userId456");
        user.setUsername("anotherUser");
        user.setRole("ROLE_ADMIN");

        loginResponse.setUser(user);

        assertEquals(user, loginResponse.getUser());
    }
}

