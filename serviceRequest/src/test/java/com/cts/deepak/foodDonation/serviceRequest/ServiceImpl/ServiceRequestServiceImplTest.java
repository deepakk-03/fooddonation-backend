package com.cts.deepak.foodDonation.serviceRequest.ServiceImpl;
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

import com.cts.deepak.foodDonation.serviceRequest.Repository.ServiceRequestRepository;
import com.cts.deepak.foodDonation.serviceRequest.feign.ServiceRequestInterface;
import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;

public class ServiceRequestServiceImplTest {

    @Mock
    private ServiceRequestRepository serviceRequestRepository;

    @Mock
    private ServiceRequestInterface serviceRequestInterface;

    @InjectMocks
    private ServiceRequestServiceImpl serviceRequestService;

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
        when(serviceRequestRepository.save(request)).thenReturn(savedRequest);

        // When
        ServiceRequest result = serviceRequestService.raiseServiceRequest(ngoId, donationId, request);

        // Then
        assertNotNull(result);
        assertEquals(savedRequest, result);
    }

    @Test
    public void testGetAllServiceRequests() {
        // Given
        List<ServiceRequest> serviceRequests = new ArrayList<>();
        when(serviceRequestRepository.findAll()).thenReturn(serviceRequests);

        // When
        List<ServiceRequest> result = serviceRequestService.getAllServiceRequests();

        // Then
        assertEquals(serviceRequests, result);
    }

    @Test
    public void testApproveServiceRequest() {
        // Given
        Long requestId = 1L;
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setStatus("Pending");
        when(serviceRequestRepository.findById(requestId)).thenReturn(Optional.of(serviceRequest));

        // When
        serviceRequestService.approveServiceRequest(requestId);

        // Then
        assertEquals("Approved", serviceRequest.getStatus());
        verify(serviceRequestRepository, times(1)).save(serviceRequest);
    }

    @Test
    public void testRejectServiceRequest() {
        // Given
        Long requestId = 1L;
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setStatus("Pending");
        when(serviceRequestRepository.findById(requestId)).thenReturn(Optional.of(serviceRequest));

        // When
        serviceRequestService.rejectServiceRequest(requestId);

        // Then
        assertEquals("Rejected", serviceRequest.getStatus());
        verify(serviceRequestRepository, times(1)).save(serviceRequest);
    }

    @Test
    public void testPendingRequest() {
        // Given
        List<ServiceRequest> pendingRequests = new ArrayList<>();
        when(serviceRequestRepository.findAllByStatus("Pending")).thenReturn(pendingRequests);

        // When
        List<ServiceRequest> result = serviceRequestService.pendingRequest();

        // Then
        assertEquals(pendingRequests, result);
    }

    @Test
    public void testDeleteRequest() {
        // Given
        Long requestId = 1L;
        ServiceRequest serviceRequest = new ServiceRequest();
        when(serviceRequestRepository.findById(requestId)).thenReturn(Optional.of(serviceRequest));

        // When
        boolean result = serviceRequestService.deleteRequest(requestId);

        // Then
        assertTrue(result);
        verify(serviceRequestRepository, times(1)).delete(serviceRequest);
    }
}
