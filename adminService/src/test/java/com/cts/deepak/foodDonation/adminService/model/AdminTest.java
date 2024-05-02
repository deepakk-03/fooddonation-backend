package com.cts.deepak.foodDonation.adminService.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

public class AdminTest {

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
    }

    @Test
    public void testGetAndSetAdminId() {
        Long id = 1L;
        admin.setAdminId(id);
        assertEquals(id, admin.getAdminId());
    }

    @Test
    public void testGetAndSetUsername() {
        String username = "dy2@gmail.com";
        admin.setUsername(username);
        assertEquals(username, admin.getUsername());
    }

    @Test
    public void testGetAndSetPassword() {
        String password = "Dy@12345";
        admin.setPassword(password);
        assertEquals(password, admin.getPassword());
    }
}
