package com.cts.deepak.foodDonation.userService.Service;

import java.util.List;

import com.cts.deepak.foodDonation.userService.model.User;

public interface UserService {
    User createUser(User user);
    List<User> getAll();
    User getById(Long userId);
	boolean deleteUserById(Long userId);
	User getUserByUsernameAndPassword(String username, String Password);
	 User getUserByUsername(String username);
	
}