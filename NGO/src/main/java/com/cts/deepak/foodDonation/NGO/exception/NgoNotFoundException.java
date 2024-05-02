package com.cts.deepak.foodDonation.NGO.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NgoNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NgoNotFoundException(String message)
	{
		super(message);
	}
}
