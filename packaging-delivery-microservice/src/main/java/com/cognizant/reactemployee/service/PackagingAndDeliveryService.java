package com.cognizant.reactemployee.service;

import org.springframework.stereotype.Service;

import com.cognizant.reactemployee.dto.PackagingAndDeliveryDTO;
import com.cognizant.reactemployee.exceptions.ComponentTypeNotFoundException;

@Service
public interface PackagingAndDeliveryService {

	PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(String Type, Integer count) throws ComponentTypeNotFoundException;
	
}
