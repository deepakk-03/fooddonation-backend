package com.cts.deepak.foodDonation.foodDonationModule.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;
import com.cts.deepak.foodDonation.foodDonationModule.Repository.FoodDonationRepository;
import com.cts.deepak.foodDonation.foodDonationModule.Service.FoodDonationService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FoodDonationServiceImpl implements FoodDonationService {
    @Autowired
    private FoodDonationRepository foodDonationRepository;

    @Override
    public FoodDonation createFoodDonation(FoodDonation foodDonation, Long userId) {
    	log.info("Donation Created");
    	foodDonation.setUserId(userId);
    	return foodDonationRepository.save(foodDonation);
    }

    @Override
    public FoodDonation getFoodDonationById(Long donationId) {
    	log.info("Donation Found");
    	return foodDonationRepository.findById(donationId).orElse(null);
    }

    @Override
    public List<FoodDonation> getAllFoodDonations() {
    	log.info("Get All Donations");
        return foodDonationRepository.findAll();
    }
    
    @Override
    public List<FoodDonation> getUnclaimedItems() {
    	log.info("Get All unclaimed Donations");
        return foodDonationRepository.findAllByStatus("Pending");
    }
    
    @Override
    public void delete(Long donationId) {
    	foodDonationRepository.deleteById(donationId);
    }

	@Override
	public FoodDonation change_status(Long id, String status) {
		FoodDonation obj = foodDonationRepository.findById(id).orElse(null);
		obj.setStatus(status);
		return foodDonationRepository.save(obj);
	}

	@Override
	public boolean deleteDonationById(Long donationId) {
		FoodDonation fd = foodDonationRepository.findById(donationId).orElse(null);
	    if (fd != null) {
	        foodDonationRepository.delete(fd);
	        return true;
	    } else {
	        return false;
	    }
	}

	@Override
	public String getFoodDescriptionById(Long donationId) {
		FoodDonation food = foodDonationRepository.findById(donationId).orElse(null);
		return food.getFoodDescription();
	}

	@Override
	public List<FoodDonation> getDonationById(Long userId) {
		return foodDonationRepository.findByUserId(userId);
	}
}