package com.cts.deepak.foodDonation.serviceRequest.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;


public class ServiceRequestTest {

    @Test
    public void testNoArgsConstructor() {
        ServiceRequest serviceRequest = new ServiceRequest();
        assertNotNull(serviceRequest);
    }

    @Test
    public void testAllArgsConstructor() {
        Long ngoId = 1L;
        Long donationId = 2L;
        Long requestId = 3L;
        String status = "Pending";

        ServiceRequest serviceRequest = new ServiceRequest(ngoId, donationId, requestId, status);

        assertEquals(ngoId, serviceRequest.getNgoId());
        assertEquals(donationId, serviceRequest.getDonationId());
        assertEquals(requestId, serviceRequest.getRequestId());
        assertEquals(status, serviceRequest.getStatus());
    }

    @Test
    public void testGettersAndSetters() {
        Long ngoId = 1L;
        Long donationId = 2L;
        Long requestId = 3L;
        String status = "Pending";

        ServiceRequest serviceRequest = new ServiceRequest();

        serviceRequest.setNgoId(ngoId);
        serviceRequest.setDonationId(donationId);
        serviceRequest.setRequestId(requestId);
        serviceRequest.setStatus(status);

        assertEquals(ngoId, serviceRequest.getNgoId());
        assertEquals(donationId, serviceRequest.getDonationId());
        assertEquals(requestId, serviceRequest.getRequestId());
        assertEquals(status, serviceRequest.getStatus());
    }

    @Test
    public void testToString() {
        Long ngoId = 1L;
        Long donationId = 2L;
        Long requestId = 3L;
        String status = "Pending";

        ServiceRequest serviceRequest = new ServiceRequest(ngoId, donationId, requestId, status);

        String expectedToString = "ServiceRequest(ngoId=" + ngoId + ", donationId=" + donationId + ", requestId=" + requestId + ", status=" + status + ")";
        assertEquals(expectedToString, serviceRequest.toString());
    }
}
