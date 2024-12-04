package com.techdome.miniLoanApp.TestModels;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.techdome.miniLoanApp.model.User;

class UserTest {

    @Test
    void testSetAndGetUsername() {
        User user = new User();
        String username = "testUser";
        user.setUsername(username);
        assertEquals(username, user.getUsername());
    }

    @Test
    void testSetAndGetPassword() {
        User user = new User();
        String password = "password123";
        user.setPassword(password);
        assertEquals(password, user.getPassword());
    }

    @Test
    void testSetAndGetRole() {
        User user = new User();
        String role = User.ROLE_ADMIN;
        user.setRole(role);
        assertEquals(role, user.getRole());
    }

    @Test
    void testSetInvalidRole() {
        User user = new User();
        assertThrows(IllegalArgumentException.class, () -> user.setRole("ROLE_INVALID"));
    }

    @Test
    void testGetAuthorities() {
        User user = new User();
        user.setRole(User.ROLE_CUSTOMER);
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        assertTrue(authorities.contains(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
    }

    @Test
    void testIsAccountNonExpired() {
        User user = new User();
        assertTrue(user.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        User user = new User();
        assertTrue(user.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        User user = new User();
        assertTrue(user.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        User user = new User();
        assertTrue(user.isEnabled());
    }

    @Test
    void testSetAndGetId() {
        User user = new User();
        String id = "user123";
        user.setId(id);
        assertEquals(id, user.getId());
    }
}

