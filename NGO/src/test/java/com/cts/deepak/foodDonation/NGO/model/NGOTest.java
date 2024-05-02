package com.cts.deepak.foodDonation.NGO.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

public class NGOTest {

    @Test
    public void testNGOConstructor() {
        // Given
        Long userId = 1L;
        Long ngoId = 2L;
        String ngoName = "ISF";
        String ngoDescription = "NGO for differently abled children";

        // When
        NGO ngo = new NGO(userId, ngoId, ngoName, ngoDescription);

        // Then
        assertNotNull(ngo);
        assertEquals(userId, ngo.getUserId());
        assertEquals(ngoId, ngo.getNgoId());
        assertEquals(ngoName, ngo.getNgoName());
        assertEquals(ngoDescription, ngo.getNgoDescription());
    }

    @Test
    public void testNGOGettersAndSetters() {
        // Given
        Long userId = 1L;
        Long ngoId = 2L;
        String ngoName = "ISF";
        String ngoDescription = "NGO for differently abled children";

        // When
        NGO ngo = new NGO();
        ngo.setUserId(userId);
        ngo.setNgoId(ngoId);
        ngo.setNgoName(ngoName);
        ngo.setNgoDescription(ngoDescription);

        // Then
        assertNotNull(ngo);
        assertEquals(userId, ngo.getUserId());
        assertEquals(ngoId, ngo.getNgoId());
        assertEquals(ngoName, ngo.getNgoName());
        assertEquals(ngoDescription, ngo.getNgoDescription());
    }
}
