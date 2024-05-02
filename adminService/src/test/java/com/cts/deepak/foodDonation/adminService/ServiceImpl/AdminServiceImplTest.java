package com.cts.deepak.foodDonation.adminService.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; 

import com.cts.deepak.foodDonation.adminService.Repository.AdminRepository;
import com.cts.deepak.foodDonation.adminService.model.Admin;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    private Admin admin;

    @BeforeEach
    public void setUp() {
        admin = new Admin();
        admin.setAdminId(1L);
        admin.setUsername("testAdmin");
        admin.setPassword("testPassword");
    }

    @Test
    public void testCreateAdmin() {
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        Admin createdAdmin = adminService.createAdmin(admin);

        assertNotNull(createdAdmin);
        assertEquals(admin, createdAdmin);
        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void testGetAdminById() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Admin retrievedAdmin = adminService.getAdminById(1L);

        assertNotNull(retrievedAdmin);
        assertEquals(admin, retrievedAdmin);
        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    public void testAuthenticate() {
        when(adminRepository.findByUsernameAndPassword("testAdmin", "testPassword")).thenReturn(admin);

        Admin authenticatedAdmin = adminService.authenticate("testAdmin", "testPassword");

        assertNotNull(authenticatedAdmin);
        assertEquals(admin, authenticatedAdmin);
        verify(adminRepository, times(1)).findByUsernameAndPassword("testAdmin", "testPassword");
    }

    @Test
    public void testGetUserByUsername() {
        when(adminRepository.findByUsername("testAdmin")).thenReturn(admin);

        Admin retrievedAdmin = adminService.getUserByUsername("testAdmin");

        assertNotNull(retrievedAdmin);
        assertEquals(admin, retrievedAdmin);
        verify(adminRepository, times(1)).findByUsername("testAdmin");
    }

    @Test
    public void testDeleteAdminById() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));
        doNothing().when(adminRepository).delete(any(Admin.class));

        boolean isDeleted = adminService.deleteAdminById(1L);

        assertTrue(isDeleted);
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, times(1)).delete(any(Admin.class));
    }

    @Test
    public void testDeleteAdminById_NotFound() {
        when(adminRepository.findById(1L)).thenReturn(Optional.empty());

        boolean isDeleted = adminService.deleteAdminById(1L);

        assertFalse(isDeleted);
        verify(adminRepository, times(1)).findById(1L);
        verify(adminRepository, never()).delete(any(Admin.class));
    }

    @Test
    public void testGetAll() {
        List<Admin> admins = new ArrayList<>();
        admins.add(admin);

        when(adminRepository.findAll()).thenReturn(admins);

        List<Admin> retrievedAdmins = adminService.getAll();

        assertNotNull(retrievedAdmins);
        assertEquals(admins, retrievedAdmins);
        verify(adminRepository, times(1)).findAll();
    }
}
