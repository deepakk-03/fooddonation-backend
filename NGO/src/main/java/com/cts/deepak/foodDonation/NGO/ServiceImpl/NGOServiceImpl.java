package com.cts.deepak.foodDonation.NGO.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.deepak.foodDonation.NGO.Repository.NGORepository;
import com.cts.deepak.foodDonation.NGO.Service.NGOService;
import com.cts.deepak.foodDonation.NGO.exception.NgoNotFoundException;
import com.cts.deepak.foodDonation.NGO.model.NGO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NGOServiceImpl implements NGOService {

    @Autowired
    private NGORepository ngoRepository;
    

    @Override
    public NGO createNGO(NGO ngo, Long userId) {
    	log.info("Inside Create NGO");
        ngo.setUserId(userId);
        return ngoRepository.save(ngo);
    }    

    @Override
    public NGO getNGOById(Long ngoId) {
    	log.info("Inside Found NGO");
    	return ngoRepository.findById(ngoId).orElse(null);
    }
    
    @Override
    public List<NGO> getAll() {
    	log.info("Inside All NGOs");
        return ngoRepository.findAll();
    }
    
    @Override
    public void delete(Long ngoId) {
    	ngoRepository.deleteById(ngoId);
    }

	@Override
	public NGO getNgoByname(String ngoName) {
		log.info("Inside Get Ngo By name");
		return ngoRepository.findByNgoName(ngoName);
	}

	@Override
	public NGO getUserByUserId(Long userId) {
		log.info("Inside Get User By User Id");
		return ngoRepository.findByUserId(userId);
	}

	@Override
	public boolean deleteNgoById(Long ngoId) {
		log.info("inside delete Ngo By Id");
		Optional<NGO> ngo = ngoRepository.findById(ngoId);
		
		if(ngo.isPresent()) {
			ngoRepository.delete(ngo.get());
			return true;
		}
		else {
			log.error("No such NGO exists, Deleting NGO failed.");
	    	throw new NgoNotFoundException("No such NGO exists");
		}
	}
}