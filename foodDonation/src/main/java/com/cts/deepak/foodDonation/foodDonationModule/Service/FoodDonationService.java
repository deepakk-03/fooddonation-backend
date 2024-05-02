package com.cts.deepak.foodDonation.foodDonationModule.Service;

import java.util.List;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;


public interface FoodDonationService {
	FoodDonation createFoodDonation(FoodDonation foodDonation, Long userID);
    FoodDonation getFoodDonationById(Long donationId);
    List<FoodDonation> getAllFoodDonations();
    void delete(Long donationId);
	FoodDonation change_status(Long id, String status);
	List<FoodDonation> getUnclaimedItems();
	boolean deleteDonationById(Long donationId);
	String getFoodDescriptionById(Long donationId);
	List<FoodDonation> getDonationById(Long userId);
}
