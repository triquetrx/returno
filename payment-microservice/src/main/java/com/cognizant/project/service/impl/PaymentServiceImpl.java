package com.cognizant.project.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.dto.CreditCardDto;
import com.cognizant.project.exception.BalanceExceptions;
import com.cognizant.project.model.CreditCard;
import com.cognizant.project.model.PaymentHistory;
import com.cognizant.project.repository.CreditCardRepository;
import com.cognizant.project.repository.PaymentHistoryRepository;
import com.cognizant.project.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private CreditCardRepository repository;
	@Autowired
	private PaymentHistoryRepository paymentHistoryRepo;

	@Override
	public boolean isCreditCardValid(CreditCardDto cardDto) {
		CreditCard creditCardDummy = repository.findById(cardDto.getCreditCardNumber()).get();
		if (creditCardDummy != null
				&& creditCardDummy.getCreditCardHolder().equalsIgnoreCase(cardDto.getCreditCardHolder())
				&& cardDto.getCvv() == creditCardDummy.getCvv()) {
			return true;
		}
		return false;
	}

	@Override
	public double getBalance(CreditCardDto cardDto,int requestId) throws BalanceExceptions {
		CreditCard creditCardDummy = repository.findById(cardDto.getCreditCardNumber()).get();
		double balance = creditCardDummy.getBalance()-cardDto.getTotalCharge()-creditCardDummy.getProcessingCharge();
		if(balance<0) {
			throw new BalanceExceptions("Can not process the transaction; Insufficient Balance");
		}
		PaymentHistory history = new PaymentHistory(requestId, cardDto.getTotalCharge()+creditCardDummy.getProcessingCharge());
		paymentHistoryRepo.save(history);
		creditCardDummy.setBalance(balance);;
		repository.save(creditCardDummy);
		return creditCardDummy.getBalance();
	}
	
	@Override
	public CreditCard getCard(CreditCardDto cardDto) {
		return repository.findById(cardDto.getCreditCardNumber()).get();
	}

}
