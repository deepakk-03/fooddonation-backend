package com.cts.deepak.foodDonation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class FoodDonationApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodDonationApplication.class, args);
	}

}
