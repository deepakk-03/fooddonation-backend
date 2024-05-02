package com.cts.deepak.foodDonation.serviceRequest.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "service_requests")
public class ServiceRequest {

	private Long ngoId;
	private Long donationId;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long requestId; 
    private String status="Pending";

}