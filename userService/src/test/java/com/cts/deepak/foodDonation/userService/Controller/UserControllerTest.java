package com.cts.deepak.foodDonation.userService.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.deepak.foodDonation.userService.Service.UserService;
import com.cts.deepak.foodDonation.userService.exception.UserNotFoundException;
import com.cts.deepak.foodDonation.userService.model.User;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        // Given
        User user = new User();
        User savedUser = new User();
        when(userService.createUser(user)).thenReturn(savedUser);

        // When
        ResponseEntity<User> response = userController.registerUser(user);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedUser, response.getBody());
    }

    @Test
    public void testCheckUsername() {
        // Given
        String username = "testuser";
        User user = new User();
        when(userService.getUserByUsername(username)).thenReturn(user);

        // When
        User result = userController.checkusername(username);

        // Then
        assertEquals(user, result);
    }

    @Test
    public void testGetUserById() {
        // Given
        Long userId = 1L;
        User user = new User();
        when(userService.getById(userId)).thenReturn(user);

        // When
        ResponseEntity<User> response = userController.getUserById(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

    @Test
    public void testGetAll() {
        // Given
        List<User> users = new ArrayList<>();
        when(userService.getAll()).thenReturn(users);

        // When
        List<User> result = userController.getAll();

        // Then
        assertEquals(users, result);
    }

    @Test
    public void testGetUserByUsername() {
        // Given
        String username = "testuser";
        String password = "password";
        User user = new User();
        when(userService.getUserByUsernameAndPassword(username, password)).thenReturn(user);

        // When
        ResponseEntity<?> response = userController.getUserByUsername(username, password);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(user, response.getBody());
    }

 
    @Test
    public void testDeleteUserById_UserNotFoundException() {
        // Given
        Long userId = 1L;
        doThrow(UserNotFoundException.class).when(userService).deleteUserById(userId);

        // When
        ResponseEntity<?> response = userController.deleteUserById(userId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
