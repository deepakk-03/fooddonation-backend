package com.cts.deepak.foodDonation.foodDonationModule.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;

@Repository
public interface FoodDonationRepository extends JpaRepository<FoodDonation, Long> {
	public List<FoodDonation> findAllByStatus(String status);

	public List<FoodDonation> findByUserId(Long userId);
}