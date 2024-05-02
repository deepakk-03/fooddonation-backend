package com.cts.deepak.foodDonation.NGO.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.deepak.foodDonation.NGO.model.NGO;

@Repository
public interface NGORepository extends JpaRepository<NGO, Long> {


	NGO findByNgoName(String ngoName);

	NGO findByUserId(Long userId); 
}
