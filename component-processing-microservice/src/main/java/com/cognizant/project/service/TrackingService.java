package com.cognizant.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.repository.ProcessResponseRepository;

@Service
public class TrackingService {
	
	@Autowired
	private ProcessResponseRepository responseRepository;
	
	public String trackRequest(int id) {
		
		return "Date of delivery: "+responseRepository.findById(id).get().getDateOfDelivery().toString();
	}

}
