package com.cts.deepak.foodDonation.NGO.Controller;

import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.deepak.foodDonation.NGO.Service.NGOService;
import com.cts.deepak.foodDonation.NGO.exception.NgoNotFoundException;
import com.cts.deepak.foodDonation.NGO.model.NGO;

@RestController
@RequestMapping("/ngos")
public class NGOController {

	@Autowired
	private NGOService ngoService;

	// Explanation: This method handles the registration of a new NGO by accepting an NGO object and a user ID.
	@PostMapping("/register/{userId}")
	public ResponseEntity<NGO> registerNGO(@RequestBody NGO ngo, @PathVariable Long userId) {
		NGO newNGO = ngoService.createNGO(ngo, userId);
		return new ResponseEntity<>(newNGO, HttpStatus.CREATED);
	}

	// Explanation: This method retrieves an NGO based on its name (provided as a path variable).
	@GetMapping("/getNgo/{ngoName}")
	public NGO getUserByNgoName(@PathVariable String ngoName) {
		return ngoService.getNgoByname(ngoName);
	}

	// Explanation: This method returns a list of all registered NGOs.
	@GetMapping("/allNgos")
	public List<NGO> getAll() {
		return ngoService.getAll();
	}

	// Explanation: This method retrieves an NGO based on the provided user ID.
	@GetMapping("/getNgoBy/{userId}")
	public NGO getUserByUserId(@PathVariable Long userId) {
		return ngoService.getUserByUserId(userId);
	}

	// Explanation: This method retrieves an NGO based on its unique ID (provided as a path variable).
	@GetMapping("/{ngoId}")
	public ResponseEntity<NGO> getNGOById(@PathVariable Long ngoId) {
		NGO ngo = ngoService.getNGOById(ngoId);
		if (ngo != null) {
			return new ResponseEntity<>(ngo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	// Explanation: This method deletes an NGO based on its unique ID (provided as a path variable).
	@DeleteMapping("/{ngoId}")
	public ResponseEntity<?> deleteNgoById(@PathVariable Long ngoId) {
		try {
			ngoService.deleteNgoById(ngoId);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (NgoNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}
}