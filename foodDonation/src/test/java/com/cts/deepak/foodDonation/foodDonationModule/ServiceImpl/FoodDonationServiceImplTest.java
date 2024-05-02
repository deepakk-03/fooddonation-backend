package com.cts.deepak.foodDonation.foodDonationModule.ServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cts.deepak.foodDonation.foodDonation.model.FoodDonation;
import com.cts.deepak.foodDonation.foodDonationModule.Repository.FoodDonationRepository;

public class FoodDonationServiceImplTest {

    @Mock
    private FoodDonationRepository foodDonationRepository;

    @InjectMocks
    private FoodDonationServiceImpl foodDonationService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateFoodDonation() {
        FoodDonation foodDonation = new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending");
        when(foodDonationRepository.save(foodDonation)).thenReturn(foodDonation);

        FoodDonation savedDonation = foodDonationService.createFoodDonation(foodDonation, 12L);
        assertNotNull(savedDonation);
        assertEquals(12L, savedDonation.getUserId());
        assertEquals("Chole", savedDonation.getFoodDescription());
    }

    @Test
    public void testGetFoodDonationById() {
        FoodDonation foodDonation = new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending");
        when(foodDonationRepository.findById(1L)).thenReturn(Optional.of(foodDonation));

        FoodDonation foundDonation = foodDonationService.getFoodDonationById(1L);
        assertNotNull(foundDonation);
        assertEquals(12L, foundDonation.getUserId());
        assertEquals("Chole", foundDonation.getFoodDescription());
    }

    @Test
    public void testGetAllFoodDonations() {
        List<FoodDonation> donations = new ArrayList<>();
        donations.add(new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending"));
        donations.add(new FoodDonation(2L, 15L, "Pasta", "Gomti Nagar, Lucknow", null, "Pending"));

        when(foodDonationRepository.findAll()).thenReturn(donations);

        List<FoodDonation> allDonations = foodDonationService.getAllFoodDonations();
        assertEquals(2, allDonations.size());
    }

    @Test
    public void testGetUnclaimedItems() {
        List<FoodDonation> donations = new ArrayList<>();
        donations.add(new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending"));
        donations.add(new FoodDonation(2L, 15L, "Pasta", "Gomti Nagar, Lucknow", null, "Pending"));

        when(foodDonationRepository.findAllByStatus("Pending")).thenReturn(donations);

        List<FoodDonation> unclaimedItems = foodDonationService.getUnclaimedItems();
        assertEquals(2, unclaimedItems.size());
    }

    @Test
    public void testDeleteDonationById() {
        when(foodDonationRepository.findById(1L)).thenReturn(Optional.of(new FoodDonation()));
        doNothing().when(foodDonationRepository).delete(any());

        boolean result = foodDonationService.deleteDonationById(1L);
        assertTrue(result);
    }

    @Test
    public void testChange_status() {
        FoodDonation foodDonation = new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending");
        when(foodDonationRepository.findById(1L)).thenReturn(Optional.of(foodDonation));
        when(foodDonationRepository.save(foodDonation)).thenReturn(foodDonation);

        FoodDonation updatedDonation = foodDonationService.change_status(1L, "Delivered");
        assertNotNull(updatedDonation);
        assertEquals("Delivered", updatedDonation.getStatus());
    }

    @Test
    public void testGetFoodDescriptionById() {
        FoodDonation foodDonation = new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending");
        when(foodDonationRepository.findById(1L)).thenReturn(Optional.of(foodDonation));

        String description = foodDonationService.getFoodDescriptionById(1L);
        assertEquals("Chole", description);
    }

    @Test
    public void testGetDonationById() {
        List<FoodDonation> donations = new ArrayList<>();
        donations.add(new FoodDonation(1L, 12L, "Chole", "Mulayam Nagar, Lucknow", null, "Pending"));
        donations.add(new FoodDonation(2L, 12L, "Pasta", "Gomti Nagar, Lucknow", null, "Pending"));

        when(foodDonationRepository.findByUserId(12L)).thenReturn(donations);

        List<FoodDonation> userDonations = foodDonationService.getDonationById(12L);
        assertEquals(2, userDonations.size());
    }
}
