package com.cognizant.project.service;

import org.springframework.stereotype.Service;

import com.cognizant.project.dto.CreditCardDto;
import com.cognizant.project.exception.BalanceExceptions;
import com.cognizant.project.model.CreditCard;

@Service
public interface PaymentService {
	
	boolean isCreditCardValid(CreditCardDto cardDto);

	double getBalance(CreditCardDto cardDto,int requestId) throws BalanceExceptions;

	CreditCard getCard(CreditCardDto cardDto);

}
