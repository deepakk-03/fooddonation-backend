package com.cts.deepak.foodDonation.userService.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.userService.Repository.UserRepository;
import com.cts.deepak.foodDonation.userService.Service.UserService;
import com.cts.deepak.foodDonation.userService.exception.UserNotFoundException;
import com.cts.deepak.foodDonation.userService.model.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
    	log.info("inside create user method");
        return userRepository.save(user);
    }

    @Override
    public User getById(Long userId) {
    	log.info("inside get user by id method");
        Optional<User> findById = userRepository.findById(userId);
        return findById.get();
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String Password) {
    	log.info("inside get User By Username And Password method");
        return userRepository.findByUsernameAndPassword(username, Password);
    }

	@Override
	public boolean deleteUserById(Long userId) {
		log.info("Using deleteUserById method");
		Optional<User> user = userRepository.findById(userId);
		    if (user.isPresent()) {
		        userRepository.delete(user.get());
		        return true;
		    } else {
		    	log.error("No such user exists, Deleting user failed.");
		    	throw new UserNotFoundException("No such user exists");
		    }
	}

	
	@Override
	public List<User> getAll() {
		log.info("inside getAll user method");
		return userRepository.findAll();
		}

	@Override
	public User getUserByUsername(String username) {
		log.info("inside get User By Username method");
		return userRepository.findByUsername(username);
	}

}