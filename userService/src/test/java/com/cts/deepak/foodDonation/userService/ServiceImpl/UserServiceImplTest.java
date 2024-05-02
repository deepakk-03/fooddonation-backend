package com.cts.deepak.foodDonation.userService.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.deepak.foodDonation.userService.Repository.UserRepository;
import com.cts.deepak.foodDonation.userService.exception.UserNotFoundException;
import com.cts.deepak.foodDonation.userService.model.User;

public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        User user = new User();
        user.setUsername("deepak@gmail.com");

        when(userRepository.save(any())).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("deepak@gmail.com", createdUser.getUsername());
    }

    @Test
    public void testGetById() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("deepak@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User retrievedUser = userService.getById(1L);

        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getUserId());
        assertEquals("deepak@gmail.com", retrievedUser.getUsername());
    }

    @Test
    public void testGetUserByUsernameAndPassword() {
        User user = new User();
        user.setUsername("deepak@gmail.com");

        when(userRepository.findByUsername("deepak@gmail.com")).thenReturn(user);

        User retrievedUser = userService.getUserByUsernameAndPassword("deepak@gmail.com", "Dy@12345");

        assertNotNull(retrievedUser);
        assertEquals("deepak@gmail.com", retrievedUser.getUsername());
    }

    @Test
    public void testDeleteUserById() {
        User user = new User();
        user.setUserId(1L);
        user.setUsername("deepak@gmail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        assertTrue(userService.deleteUserById(1L));
    }

    @Test
    public void testDeleteUserById_NotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, () -> userService.deleteUserById(1L));
    }

    @Test
    public void testGetAll() {
        User user1 = new User();
        user1.setUserId(1L);
        user1.setUsername("deepak@gmail.com");
        User user2 = new User();
        user2.setUserId(2L);
        user2.setUsername("user2");

        List<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user2);

        when(userRepository.findAll()).thenReturn(userList);

        List<User> retrievedUsers = userService.getAll();

        assertNotNull(retrievedUsers);
        assertEquals(2, retrievedUsers.size());
    }

    @Test
    public void testGetUserByUsername() {
        User user = new User();
        user.setUsername("deepak@gmail.com");

        when(userRepository.findByUsername("deepak@gmail.com")).thenReturn(user);

        User retrievedUser = userService.getUserByUsername("deepak@gmail.com");

        assertNotNull(retrievedUser);
        assertEquals("deepak@gmail.com", retrievedUser.getUsername());
    }
}
