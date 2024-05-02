package com.cts.deepak.foodDonation.adminService.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.adminService.AdminNotFoundException.AdminNotFoundException;
import com.cts.deepak.foodDonation.adminService.Repository.AdminRepository;
import com.cts.deepak.foodDonation.adminService.Service.AdminService;
import com.cts.deepak.foodDonation.adminService.model.Admin;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminRepository adminRepository;

	@Override
	public Admin createAdmin(Admin admin) {
		log.info("Inside create Admin");
		return adminRepository.save(admin);
	}

	@Override
	public Admin getAdminById(Long adminId) {
		log.info("Inside get Admin By Id");
		return adminRepository.findById(adminId).orElse(null);
	}

	@Override
	public Admin authenticate(String username, String password) {
		log.info("Inside authenticate");
		Admin admin = adminRepository.findByUsername(username);
		if (admin != null) {
			return adminRepository.findByUsernameAndPassword(username, password);
		} else {
			return null;
		}
	}

	@Override
	public Admin getUserByUsername(String username) {
		log.info("Inside get User By Username");
		return adminRepository.findByUsername(username);
	}

	@Override
	public boolean deleteAdminById(Long adminId) {
		log.info("Using deleteAdminById method");
		Optional<Admin> admin = adminRepository.findById(adminId);
		if (admin.isPresent()) {
			adminRepository.delete(admin.get());
			return true;
		} else {
			log.error("No such Amin exists, Deleting Admin failed.");
			throw new AdminNotFoundException("No such Amin exists");
		}
	}

	@Override
	public List<Admin> getAll() {
		log.info("Inside get All Admin");
		return adminRepository.findAll();
	}
}