package com.cts.deepak.foodDonation.serviceRequest.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;

@Service
public interface ServiceRequestService {
    ServiceRequest raiseServiceRequest(Long ngoId,Long donationId, ServiceRequest request);
    List<ServiceRequest> getAllServiceRequests();
    void approveServiceRequest(Long requestId);
    void rejectServiceRequest(Long requestId);
	List<ServiceRequest> pendingRequest();
	boolean deleteRequest(Long requestId);
    
//    void updateServiceRequestStatus(Long requestId, String status);
}