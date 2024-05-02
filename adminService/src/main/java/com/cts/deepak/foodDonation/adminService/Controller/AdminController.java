package com.cts.deepak.foodDonation.adminService.Controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.deepak.foodDonation.adminService.AdminNotFoundException.AdminNotFoundException;
import com.cts.deepak.foodDonation.adminService.Service.AdminService;
import com.cts.deepak.foodDonation.adminService.model.Admin;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;

	// Register a new admin
	@PostMapping("/register")
	public Admin registerAdmin(@RequestBody Admin admin) {
		Admin newAdmin = adminService.createAdmin(admin);
		return newAdmin;
	}

	// Get all admins
	@GetMapping("/allAdmin")
	public List<Admin> getAll() {
		return adminService.getAll();
	}

	// Check if an admin username exists
	@GetMapping("/checkAdminUsername/{username}")
	public Admin checkusername(@PathVariable String username) {
		return adminService.getUserByUsername(username);
	}

	// Get admin by ID
	@GetMapping("/{adminId}")
	public ResponseEntity<Admin> getAdminById(@PathVariable Long adminId) {
		Admin admin = adminService.getAdminById(adminId);
		if (admin != null) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Authenticate admin
	@PostMapping("/authenticate")
	public ResponseEntity<?> authenticateAdmin(@RequestParam String username, @RequestParam String password) {
		String un = URLDecoder.decode(username, StandardCharsets.UTF_8);
		String pass = URLDecoder.decode(password, StandardCharsets.UTF_8);

		Admin admin = adminService.authenticate(un, pass);
		if (admin != null) {
			return new ResponseEntity<>(admin, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No such admin", HttpStatus.UNAUTHORIZED);
		}
	}

	// Delete an admin by ID
	@DeleteMapping("/{adminId}")
	public ResponseEntity<?> deleteAdminById(@PathVariable Long adminId) {

		try {
			adminService.deleteAdminById(adminId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (AdminNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}
