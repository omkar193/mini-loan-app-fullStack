package com.techdome.miniLoanApp.TestRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.techdome.miniLoanApp.model.User;
import com.techdome.miniLoanApp.repository.UserRepository;

@SpringBootTest
class UserRepositoryTest {

    @Mock
    private UserRepository userRepository;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId("1");
        user.setUsername("testuser");
        user.setPassword("password");
        user.setRole(User.ROLE_CUSTOMER);
    }

    @Test
    void testFindByUsername() {
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(user));

        Optional<User> returnedUser = userRepository.findByUsername("testuser");

        assertTrue(returnedUser.isPresent());
        assertEquals("testuser", returnedUser.get().getUsername());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void testFindByUsernameNotFound() {
        when(userRepository.findByUsername("nonexistentuser")).thenReturn(Optional.empty());

        Optional<User> returnedUser = userRepository.findByUsername("nonexistentuser");

        assertFalse(returnedUser.isPresent());
        verify(userRepository, times(1)).findByUsername("nonexistentuser");
    }

    @Test
    void testCountUsersByRole() {
        when(userRepository.countUsersByRole(User.ROLE_CUSTOMER)).thenReturn(5L);

        long userCount = userRepository.countUsersByRole(User.ROLE_CUSTOMER);

        assertEquals(5L, userCount);
        verify(userRepository, times(1)).countUsersByRole(User.ROLE_CUSTOMER);
    }

    @Test
    void testCountUsersByRoleAdmin() {
        when(userRepository.countUsersByRole(User.ROLE_ADMIN)).thenReturn(2L);

        long userCount = userRepository.countUsersByRole(User.ROLE_ADMIN);

        assertEquals(2L, userCount);
        verify(userRepository, times(1)).countUsersByRole(User.ROLE_ADMIN);
    }
}

