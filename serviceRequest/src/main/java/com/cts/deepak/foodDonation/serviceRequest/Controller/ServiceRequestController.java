package com.cts.deepak.foodDonation.serviceRequest.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cts.deepak.foodDonation.serviceRequest.Service.ServiceRequestService;
import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;


@RestController
@RequestMapping("/service-requests")
public class ServiceRequestController {

    @Autowired
    private ServiceRequestService serviceRequestService;

    @PostMapping("/raise/{ngoId}/{donationId}")
    public ResponseEntity<ServiceRequest> raiseServiceRequest(@PathVariable Long ngoId, @PathVariable Long donationId, @RequestBody ServiceRequest request ) {
    	
        ServiceRequest serviceRequest = serviceRequestService.raiseServiceRequest(ngoId, donationId,request);
        return new ResponseEntity<>(serviceRequest, HttpStatus.CREATED);
    }
    
    @GetMapping("/pendingRequest")
    public ResponseEntity<List<ServiceRequest>> pendingRequest(){
    	List<ServiceRequest> sr = serviceRequestService.pendingRequest();
    	return new ResponseEntity<>(sr, HttpStatus.OK);
    }
    
    
    @GetMapping("/all")
    public ResponseEntity<List<ServiceRequest>> getAllServiceRequests() {
        List<ServiceRequest> serviceRequests = serviceRequestService.getAllServiceRequests();
        return new ResponseEntity<>(serviceRequests, HttpStatus.OK);
    }

    @PutMapping("/approve/{requestId}")
    public ResponseEntity<Void> approveServiceRequest(@PathVariable Long requestId) {
        serviceRequestService.approveServiceRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/reject/{requestId}")
    public ResponseEntity<Void> rejectServiceRequest(@PathVariable Long requestId) {
        serviceRequestService.rejectServiceRequest(requestId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    
    @DeleteMapping("/delete/{requestId}")
    public ResponseEntity<Void> deleteRequest(@PathVariable Long requestId) {
    	boolean isRemoved = serviceRequestService.deleteRequest(requestId);
    	
    	if(isRemoved) {
    		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    } else {
	        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	    }
    }
}