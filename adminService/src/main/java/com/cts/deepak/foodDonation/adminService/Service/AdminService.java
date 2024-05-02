package com.cts.deepak.foodDonation.adminService.Service;

import java.util.List;

import com.cts.deepak.foodDonation.adminService.model.Admin;

public interface AdminService {
    Admin createAdmin(Admin admin);
    Admin getAdminById(Long adminId);
    Admin authenticate(String username, String password);
	Admin getUserByUsername(String username);
	List<Admin> getAll();
	boolean deleteAdminById(Long adminId);
}
