package com.cts.deepak.foodDonation.foodDonation.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.Date;

public class FoodDonationTest {

    @Test
    public void testFoodDonationConstructor() {
        // Create a FoodDonation object with sample data
        Long donationId = 1L;
        Long userId = 12L;
        String foodDescription = "Chole";
        String location = "4131, T-4, TVH, Padur, Chennai";
        Date pickupDate = new Date();
        String status = "Pending";

        FoodDonation foodDonation = new FoodDonation(donationId, userId, foodDescription, location, pickupDate, status);

        // Check if the object is initialized correctly
        assertEquals(donationId, foodDonation.getDonationId());
        assertEquals(userId, foodDonation.getUserId());
        assertEquals(foodDescription, foodDonation.getFoodDescription());
        assertEquals(location, foodDonation.getLocation());
        assertEquals(pickupDate, foodDonation.getPickupDate());
        assertEquals(status, foodDonation.getStatus());
    }

    @Test
    public void testFoodDonationDefaultConstructor() {
        // Create a FoodDonation object with default constructor
        FoodDonation foodDonation = new FoodDonation();

        // Check if the object is initialized with default values
        assertEquals(null, foodDonation.getDonationId());
        assertEquals(null, foodDonation.getUserId());
        assertEquals(null, foodDonation.getFoodDescription());
        assertEquals(null, foodDonation.getLocation());
        assertEquals(null, foodDonation.getPickupDate());
        assertEquals("Pending", foodDonation.getStatus());
    }
}
