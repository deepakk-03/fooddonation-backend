package com.cts.deepak.foodDonation.serviceRequest.model;

import java.util.Date;

 
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FoodDonation {
    private Long donationId;

    private Long userId;

    private String foodDescription;
    private String location;
    private Date pickupDate;
    private String status;

    
}