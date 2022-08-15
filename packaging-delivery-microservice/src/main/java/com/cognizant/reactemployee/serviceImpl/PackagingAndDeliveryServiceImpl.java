package com.cognizant.reactemployee.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.reactemployee.dto.PackagingAndDeliveryDTO;
import com.cognizant.reactemployee.exceptions.ComponentTypeNotFoundException;
import com.cognizant.reactemployee.model.PackagingAndDelivery;
import com.cognizant.reactemployee.service.PackagingAndDeliveryService;

@Service
public class PackagingAndDeliveryServiceImpl implements PackagingAndDeliveryService{
	
	private double totalCharge;
	private Logger log = LoggerFactory.getLogger(getClass());
	@Autowired
	PackagingAndDelivery packagingAndDelivery;

	@Override
	public PackagingAndDeliveryDTO calculatePackagingAndDeliveryCharge(String type, Integer count) throws ComponentTypeNotFoundException {
		
		log.info("*".repeat(10)+"Calculating Packaging & Delivery Charge"+"*".repeat(10));
		if (("Integral").equalsIgnoreCase(type)) {
			log.info("Integral");
			packagingAndDelivery.setPackagingCost(100);
			packagingAndDelivery.setDeliveryCost(200);

		} else if (("Accessory").equalsIgnoreCase(type)) {
			log.info("Accessory");
			packagingAndDelivery.setPackagingCost(50);
			packagingAndDelivery.setDeliveryCost(100);
		} else if (("Protective sheath").equalsIgnoreCase(type)) {
			packagingAndDelivery.setPackagingCost(50);
		} else {
			throw new ComponentTypeNotFoundException("Component Type: " + type + "not found.");
		}
		
		totalCharge = (packagingAndDelivery.getDeliveryCost() + packagingAndDelivery.getDeliveryCost())*count;
		return new PackagingAndDeliveryDTO(packagingAndDelivery.getDeliveryCost(),packagingAndDelivery.getDeliveryCost(),totalCharge);
		
	}
	
}
