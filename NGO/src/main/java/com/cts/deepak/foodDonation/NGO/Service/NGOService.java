package com.cts.deepak.foodDonation.NGO.Service;

import java.util.List;

import com.cts.deepak.foodDonation.NGO.model.NGO;

public interface NGOService {
    NGO createNGO(NGO ngo, Long userId);
    NGO getNGOById(Long ngoId);
    List<NGO> getAll();
    void delete(Long ngoId);
	NGO getNgoByname(String ngoName);
	NGO getUserByUserId(Long userId);
	boolean deleteNgoById(Long ngoId);
}