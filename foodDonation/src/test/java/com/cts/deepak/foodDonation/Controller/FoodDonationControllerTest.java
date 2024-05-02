package com.cts.deepak.foodDonation.Controller;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList; 
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;
import com.cts.deepak.foodDonation.foodDonationModule.Service.FoodDonationService;
 
public class FoodDonationControllerTest {

    @Mock
    private FoodDonationService foodDonationService;

    @InjectMocks
    private FoodDonationController foodDonationController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFoodDonation() {
        // Given
        Long userId = 1L;
        FoodDonation foodDonation = new FoodDonation();
        FoodDonation newFoodDonation = new FoodDonation();
        when(foodDonationService.createFoodDonation(foodDonation, userId)).thenReturn(newFoodDonation);

        // When
        ResponseEntity<FoodDonation> response = foodDonationController.createFoodDonation(foodDonation, userId);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(newFoodDonation, response.getBody());
    }

    @Test
    public void testGetFoodDonationById() {
        // Given
        Long donationId = 1L;
        FoodDonation foodDonation = new FoodDonation();
        when(foodDonationService.getFoodDonationById(donationId)).thenReturn(foodDonation);

        // When
        ResponseEntity<FoodDonation> response = foodDonationController.getFoodDonationById(donationId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDonation, response.getBody());
    }

    @Test
    public void testGetFoodDescriptionById() {
        // Given
        Long donationId = 1L;
        String foodDescription = "Test Food Description";
        when(foodDonationService.getFoodDescriptionById(donationId)).thenReturn(foodDescription);

        // When
        ResponseEntity<String> response = foodDonationController.getFoodDescriptionById(donationId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDescription, response.getBody());
    }

    @Test
    public void testGetDonationById() {
        // Given
        Long userId = 1L;
        List<FoodDonation> foodDonations = new ArrayList<>();
        when(foodDonationService.getDonationById(userId)).thenReturn(foodDonations);

        // When
        ResponseEntity<List<FoodDonation>> response = foodDonationController.getDonationById(userId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDonations, response.getBody());
    }

    @Test
    public void testChangeStatus() {
        // Given
        Long id = 1L;
        String status = "Completed";
        FoodDonation foodDonation = new FoodDonation();
        when(foodDonationService.change_status(id, status)).thenReturn(foodDonation);

        // When
        ResponseEntity<FoodDonation> response = foodDonationController.change_status(id, status);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDonation, response.getBody());
    }

    @Test
    public void testUnclaimedItems() {
        // Given
        List<FoodDonation> foodDonations = new ArrayList<>();
        when(foodDonationService.getUnclaimedItems()).thenReturn(foodDonations);

        // When
        ResponseEntity<List<FoodDonation>> response = foodDonationController.unclaimedItems();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDonations, response.getBody());
    }

    @Test
    public void testGetAllFoodDonations() {
        // Given
        List<FoodDonation> foodDonations = new ArrayList<>();
        when(foodDonationService.getAllFoodDonations()).thenReturn(foodDonations);

        // When
        ResponseEntity<List<FoodDonation>> response = foodDonationController.getAllFoodDonations();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(foodDonations, response.getBody());
    }

    @Test
    public void testDeleteDonationById() {
        // Given
        Long donationId = 1L;
        when(foodDonationService.deleteDonationById(donationId)).thenReturn(true);

        // When
        ResponseEntity<Void> response = foodDonationController.deleteDonationById(donationId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
