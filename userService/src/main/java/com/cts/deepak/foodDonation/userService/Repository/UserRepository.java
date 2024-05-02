package com.cts.deepak.foodDonation.userService.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.deepak.foodDonation.userService.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);

	User findByUsernameAndPassword(String username, String password);
}