package com.cts.deepak.foodDonation.NGO.Controller;
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

import com.cts.deepak.foodDonation.NGO.Service.NGOService;
import com.cts.deepak.foodDonation.NGO.exception.NgoNotFoundException;
import com.cts.deepak.foodDonation.NGO.model.NGO;

public class NGOControllerTest {

    @Mock
    private NGOService ngoService;

    @InjectMocks
    private NGOController ngoController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterNGO() {
        // Given
        NGO ngo = new NGO();
        ngo.setNgoName("ISF");
        ngo.setNgoDescription("NGO for differently abled children");
        when(ngoService.createNGO(ngo, 1L)).thenReturn(ngo);

        // When
        ResponseEntity<NGO> response = ngoController.registerNGO(ngo, 1L);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ngo, response.getBody());
    }

    @Test
    public void testGetUserByNgoName() {
        // Given
        String ngoName = "ISF";
        NGO ngo = new NGO();
        ngo.setNgoName(ngoName);
        when(ngoService.getNgoByname(ngoName)).thenReturn(ngo);

        // When
        NGO result = ngoController.getUserByNgoName(ngoName);

        // Then
        assertNotNull(result);
        assertEquals(ngoName, result.getNgoName());
    }

    @Test
    public void testGetAll() {
        // Given
        List<NGO> ngoList = new ArrayList<>();
        ngoList.add(new NGO());
        when(ngoService.getAll()).thenReturn(ngoList);

        // When
        List<NGO> result = ngoController.getAll();

        // Then
        assertNotNull(result);
        assertEquals(ngoList.size(), result.size());
    }

    @Test
    public void testGetUserByUserId() {
        // Given
        Long userId = 1L;
        NGO ngo = new NGO();
        ngo.setUserId(userId);
        when(ngoService.getUserByUserId(userId)).thenReturn(ngo);

        // When
        NGO result = ngoController.getUserByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }

    @Test
    public void testGetNGOById() {
        // Given
        Long ngoId = 1L;
        NGO ngo = new NGO();
        ngo.setNgoId(ngoId);
        when(ngoService.getNGOById(ngoId)).thenReturn(ngo);

        // When
        ResponseEntity<NGO> response = ngoController.getNGOById(ngoId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(ngo, response.getBody());
    }

    
    @Test
    public void testDeleteNgoById_NgoNotFoundException() {
        // Given
        Long ngoId = 1L;
        doThrow(new NgoNotFoundException("NGO not found with ID: " + ngoId)).when(ngoService).deleteNgoById(ngoId);

        // When
        ResponseEntity<?> response = ngoController.deleteNgoById(ngoId);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
