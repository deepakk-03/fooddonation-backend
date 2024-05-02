package com.cts.deepak.foodDonation.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;
import com.cts.deepak.foodDonation.foodDonationModule.Service.FoodDonationService;

@RestController
@RequestMapping("/food-donations")
public class FoodDonationController {

	@Autowired
	private FoodDonationService foodDonationService;

	// Create a new food donation
	@PostMapping("/create/{userId}")
	public ResponseEntity<FoodDonation> createFoodDonation(@RequestBody FoodDonation foodDonation,
			@PathVariable Long userId) {
		FoodDonation newFoodDonation = foodDonationService.createFoodDonation(foodDonation, userId);
		return new ResponseEntity<>(newFoodDonation, HttpStatus.CREATED);
	}

	// Get food donation by ID
	@GetMapping("/{donationId}")
	public ResponseEntity<FoodDonation> getFoodDonationById(@PathVariable Long donationId) {
		FoodDonation foodDonation = foodDonationService.getFoodDonationById(donationId);
		if (foodDonation != null) {
			return new ResponseEntity<>(foodDonation, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get food description by donation ID
	@GetMapping("/description/{donationId}")
	public ResponseEntity<String> getFoodDescriptionById(@PathVariable Long donationId) {
		String foodDes = foodDonationService.getFoodDescriptionById(donationId);
		if (foodDes != null) {
			return new ResponseEntity<>(foodDes, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Get all donations by user ID
	@GetMapping("/getDonationById/{userId}")
	public ResponseEntity<List<FoodDonation>> getDonationById(@PathVariable Long userId) {
		List<FoodDonation> foodDonations = foodDonationService.getDonationById(userId);
		return new ResponseEntity<>(foodDonations, HttpStatus.OK);
	}
	
	// Change status of a donation
	@PutMapping("/changeStatus/{id}/{status}")
	public ResponseEntity<FoodDonation> change_status(@PathVariable Long id, @PathVariable String status) {
		FoodDonation foodDonation = foodDonationService.change_status(id, status);
		return new ResponseEntity<>(foodDonation, HttpStatus.OK);
	}
	
	// Get unclaimed food items
	@GetMapping("/unclaim")
	public ResponseEntity<List<FoodDonation>> unclaimedItems() {
		List<FoodDonation> foodDonations = foodDonationService.getUnclaimedItems();
		return new ResponseEntity<>(foodDonations, HttpStatus.OK);
	}
	
	// Get all food donations
	@GetMapping("/all")
	public ResponseEntity<List<FoodDonation>> getAllFoodDonations() {
		List<FoodDonation> foodDonations = foodDonationService.getAllFoodDonations();
		return new ResponseEntity<>(foodDonations, HttpStatus.OK);
	}

	// Delete a donation by ID
	@DeleteMapping("/{donationId}")
	public ResponseEntity<Void> deleteDonationById(@PathVariable Long donationId) {
		boolean isRemoved = foodDonationService.deleteDonationById(donationId);
		if (isRemoved) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}