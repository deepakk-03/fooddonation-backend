package com.cts.deepak.foodDonation.adminService.AdminNotFoundException;


import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AdminNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AdminNotFoundException(String message)
	{
		super(message);
	}
}