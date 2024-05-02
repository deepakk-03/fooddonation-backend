package com.cts.deepak.foodDonation.serviceRequest.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.cts.deepak.foodDonation.serviceRequest.model.FoodDonation;

@FeignClient("FOODDONATION")
public interface ServiceRequestInterface {

	 @PutMapping("/food-donations/changeStatus/{id}/{status}")
	 public ResponseEntity<FoodDonation> change_status(@PathVariable Long id, @PathVariable String status);
}
