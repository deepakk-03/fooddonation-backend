package com.cts.deepak.foodDonation.serviceRequest.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Modifying;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.deepak.foodDonation.serviceRequest.model.ServiceRequest;

@Repository
public interface ServiceRequestRepository extends JpaRepository<ServiceRequest, Long> {

	List<ServiceRequest> findAllByStatus(String status);
	
}