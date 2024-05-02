package com.cts.deepak.foodDonation.userService.Controller;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.deepak.foodDonation.userService.Service.UserService;
import com.cts.deepak.foodDonation.userService.exception.UserNotFoundException;
import com.cts.deepak.foodDonation.userService.model.User;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestBody User user) {
		User newUser = userService.createUser(user);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/checkusername/{username}")
	public User checkusername(@PathVariable String username) {
		return userService.getUserByUsername(username);
	}

	@GetMapping("user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable Long userId) {
		User user = userService.getById(userId);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	@GetMapping("/allUsers")
    public List<User> getAll() {
        return userService.getAll();
    }

	@PostMapping("/login")
	public ResponseEntity<?> getUserByUsername(@RequestParam String username, @RequestParam String password) {
		String un = URLDecoder.decode(username, StandardCharsets.UTF_8);
		String pass = URLDecoder.decode(password, StandardCharsets.UTF_8);
		
		User user = userService.getUserByUsernameAndPassword(un, pass);
		if (user != null) {
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			return new ResponseEntity<>("No such user",HttpStatus.NOT_FOUND);
		}
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUserById(@PathVariable Long userId) {
	   try {
		   userService.deleteUserById(userId);
		   return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	   }catch(UserNotFoundException e) {
		   return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
	   }
	}
 }