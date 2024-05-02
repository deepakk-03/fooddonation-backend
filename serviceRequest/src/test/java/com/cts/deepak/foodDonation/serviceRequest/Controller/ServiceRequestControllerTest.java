package com.cts.deepak.foodDonation.serviceRequest.Controller;
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

import com.cts.deepak.foodDonation.serviceRequest.Service.ServiceRequestService;
import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;

public class ServiceRequestControllerTest {

    @Mock
    private ServiceRequestService serviceRequestService;

    @InjectMocks
    private ServiceRequestController serviceRequestController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRaiseServiceRequest() {
        // Given
        Long ngoId = 1L;
        Long donationId = 2L;
        ServiceRequest request = new ServiceRequest();
        ServiceRequest savedRequest = new ServiceRequest();
        when(serviceRequestService.raiseServiceRequest(ngoId, donationId, request)).thenReturn(savedRequest);

        // When
        ResponseEntity<ServiceRequest> response = serviceRequestController.raiseServiceRequest(ngoId, donationId, request);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(savedRequest, response.getBody());
    }

    @Test
    public void testPendingRequest() {
        // Given
        List<ServiceRequest> pendingRequests = new ArrayList<>();
        when(serviceRequestService.pendingRequest()).thenReturn(pendingRequests);

        // When
        ResponseEntity<List<ServiceRequest>> response = serviceRequestController.pendingRequest();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(pendingRequests, response.getBody());
    }

    @Test
    public void testGetAllServiceRequests() {
        // Given
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        when(serviceRequestService.getAllServiceRequests()).thenReturn(serviceRequests);

        // When
        ResponseEntity<List<ServiceRequest>> response = serviceRequestController.getAllServiceRequests();

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(serviceRequests, response.getBody());
    }

    @Test
    public void testApproveServiceRequest() {
        // Given
        Long requestId = 1L;
        doNothing().when(serviceRequestService).approveServiceRequest(requestId);

        // When
        ResponseEntity<Void> response = serviceRequestController.approveServiceRequest(requestId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testRejectServiceRequest() {
        // Given
        Long requestId = 1L;
        doNothing().when(serviceRequestService).rejectServiceRequest(requestId);

        // When
        ResponseEntity<Void> response = serviceRequestController.rejectServiceRequest(requestId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testDeleteRequest() {
        // Given
        Long requestId = 1L;
        when(serviceRequestService.deleteRequest(requestId)).thenReturn(true);

        // When
        ResponseEntity<Void> response = serviceRequestController.deleteRequest(requestId);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
