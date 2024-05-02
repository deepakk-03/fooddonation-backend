package com.cts.deepak.foodDonation.serviceRequest.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.serviceRequest.Repository.ServiceRequestRepository;
import com.cts.deepak.foodDonation.serviceRequest.Service.ServiceRequestService;
import com.cts.deepak.foodDonation.serviceRequest.feign.ServiceRequestInterface;
import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ServiceRequestServiceImpl implements ServiceRequestService {

	@Autowired
    private ServiceRequestRepository serviceRequestRepository;
	
	@Autowired
	ServiceRequestInterface serviceRequestInterface;


    @Override
    public ServiceRequest raiseServiceRequest(Long ngoId, Long donationId, ServiceRequest request) {
    	
    	log.info("inside Raised Request");
        ServiceRequest serviceRequest = new ServiceRequest();
        serviceRequest.setNgoId(ngoId);
        serviceRequest.setDonationId(donationId);
       

        return serviceRequestRepository.save(request);
    }

    @Override
    public List<ServiceRequest> getAllServiceRequests() {
    	log.info("inside get All Service Requests");
        return serviceRequestRepository.findAll();
    }

    @Override
    public void approveServiceRequest(Long requestId) {
    	log.info("inside approve Service Request");
        ServiceRequest serviceRequest = serviceRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Service Request not found"));
        serviceRequest.setStatus("Approved");
        
        serviceRequestInterface.change_status(serviceRequest.getDonationId(), "Approved");
        
        serviceRequestRepository.save(serviceRequest);
    }

    @Override
    public void rejectServiceRequest(Long requestId) {
    	log.info("inside reject Service Request");
        ServiceRequest serviceRequest = serviceRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Service Request not found"));
        serviceRequest.setStatus("Rejected");
        
        serviceRequestInterface.change_status(serviceRequest.getDonationId(), "Rejected");
        
        serviceRequestRepository.save(serviceRequest);
    }

	@Override
	public List<ServiceRequest> pendingRequest() {
		return serviceRequestRepository.findAllByStatus("Pending");
		
	}

	@Override
	public boolean deleteRequest(Long requestId) {
		log.info("inside delete Request");
		Optional<ServiceRequest> admin = serviceRequestRepository.findById(requestId);
	    if (admin.isPresent()) {
	        serviceRequestRepository.delete(admin.get());
	        return true;
	    } else {
	        return false;
	    }
	}

}