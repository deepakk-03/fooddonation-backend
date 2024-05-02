package com.cts.deepak.foodDonation.adminService.Controller;

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

import com.cts.deepak.foodDonation.adminService.AdminNotFoundException.AdminNotFoundException;
import com.cts.deepak.foodDonation.adminService.Service.AdminService;
import com.cts.deepak.foodDonation.adminService.model.Admin;

public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterAdmin() {
        // Given
        Admin admin = new Admin();
        Admin newAdmin = new Admin();
        when(adminService.createAdmin(admin)).thenReturn(newAdmin);

        // When
        Admin result = adminController.registerAdmin(admin);

        // Then
        assertEquals(newAdmin, result);
    }

    @Test
    public void testGetAll() {
        // Given
        List<Admin> admins = new ArrayList<>();
        when(adminService.getAll()).thenReturn(admins);

        // When
        List<Admin> result = adminController.getAll();

        // Then
        assertEquals(admins, result);
    }

    @Test
    public void testCheckusername() {
        // Given
        String username = "testusername";
        Admin admin = new Admin();
        when(adminService.getUserByUsername(username)).thenReturn(admin);

        // When
        Admin result = adminController.checkusername(username);

        // Then
        assertEquals(admin, result);
    }

    @Test
    public void testGetAdminById() {
        // Given
        Long adminId = 1L;
        Admin admin = new Admin();
        when(adminService.getAdminById(adminId)).thenReturn(admin);

        // When
        ResponseEntity<Admin> result = adminController.getAdminById(adminId);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(admin, result.getBody());
    }

    @Test
    public void testAuthenticateAdmin() {
        // Given
        String username = "testusername";
        String password = "testpassword";
        Admin admin = new Admin();
        when(adminService.authenticate(username, password)).thenReturn(admin);

        // When
        ResponseEntity<?> result = adminController.authenticateAdmin(username, password);

        // Then
        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(admin, result.getBody());
    }


    @Test
    public void testDeleteAdminById_NotFound() {
        // Given
        Long adminId = 1L;
        doThrow(new AdminNotFoundException("Admin not found")).when(adminService).deleteAdminById(adminId);

        // When
        ResponseEntity<?> result = adminController.deleteAdminById(adminId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        assertEquals("Admin not found", result.getBody());
    }
}
