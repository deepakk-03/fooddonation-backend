 package com.cts.deepak.foodDonation.NGO.ServiceImpl;
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

import com.cts.deepak.foodDonation.NGO.Repository.NGORepository;
import com.cts.deepak.foodDonation.NGO.exception.NgoNotFoundException;
import com.cts.deepak.foodDonation.NGO.model.NGO;

public class NGOServiceImplTest {

    @Mock
    private NGORepository ngoRepository;

    @InjectMocks
    private NGOServiceImpl ngoService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateNGO() {
        // Given
        Long userId = 1L;
        NGO ngo = new NGO();
        ngo.setUserId(userId);
        when(ngoRepository.save(ngo)).thenReturn(ngo);

        // When
        NGO result = ngoService.createNGO(ngo, userId);

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
        when(ngoRepository.findById(ngoId)).thenReturn(Optional.of(ngo));

        // When
        NGO result = ngoService.getNGOById(ngoId);

        // Then
        assertNotNull(result);
        assertEquals(ngoId, result.getNgoId());
    }

    @Test
    public void testGetAll() {
        // Given
        List<NGO> ngoList = new ArrayList<>();
        ngoList.add(new NGO());
        when(ngoRepository.findAll()).thenReturn(ngoList);

        // When
        List<NGO> result = ngoService.getAll();

        // Then
        assertNotNull(result);
        assertEquals(ngoList.size(), result.size());
    }

    @Test
    public void testDeleteNgoById() {
        // Given
        Long ngoId = 1L;
        when(ngoRepository.findById(ngoId)).thenReturn(Optional.of(new NGO()));

        // When
        boolean result = ngoService.deleteNgoById(ngoId);

        // Then
        assertTrue(result);
        verify(ngoRepository, times(1)).delete(any(NGO.class));
    }

    @Test
    public void testDeleteNgoById_NgoNotFoundException() {
        // Given
        Long ngoId = 1L;
        when(ngoRepository.findById(ngoId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(NgoNotFoundException.class, () -> ngoService.deleteNgoById(ngoId));
    }

    @Test
    public void testGetNgoByname() {
        // Given
        String ngoName = "ISF";
        NGO ngo = new NGO();
        ngo.setNgoName(ngoName);
        when(ngoRepository.findByNgoName(ngoName)).thenReturn(ngo);

        // When
        NGO result = ngoService.getNgoByname(ngoName);

        // Then
        assertNotNull(result);
        assertEquals(ngoName, result.getNgoName());
    }

    @Test
    public void testGetUserByUserId() {
        // Given
        Long userId = 1L;
        NGO ngo = new NGO();
        ngo.setUserId(userId);
        when(ngoRepository.findByUserId(userId)).thenReturn(ngo);

        // When
        NGO result = ngoService.getUserByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
    }
}
