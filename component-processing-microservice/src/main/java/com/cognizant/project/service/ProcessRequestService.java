package com.cognizant.project.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.project.client.PackagingAndDeliveryClient;
import com.cognizant.project.client.TransactionClient;
import com.cognizant.project.dto.CreditCardDto;
import com.cognizant.project.dto.PackagingAndDeliveryDTO;
import com.cognizant.project.dto.ProcessRequestDTO;
import com.cognizant.project.exception.PriorityException;
import com.cognizant.project.model.ProcessRequest;
import com.cognizant.project.model.ProcessResponse;
import com.cognizant.project.repository.ProcessRequestRepository;
import com.cognizant.project.repository.ProcessResponseRepository;

@Service
public class ProcessRequestService {

	@Autowired
	private ProcessResponseRepository responseRepository;
	@Autowired
	private ProcessRequestRepository requestRepository;
	@Autowired
	private PackagingAndDeliveryClient deliveryClient;
	@Autowired
	private TransactionClient transactionClient;
	private double totalCharge = 0;

	public ProcessResponse processDetails(ProcessRequestDTO requestDTO, String token) throws PriorityException {

		double processingCharge = 0;
		Calendar calendar = Calendar.getInstance();
		if (requestDTO.getComponentType().equalsIgnoreCase("Integral")) {
			if (requestDTO.isPriorityRequest()) {
				processingCharge = 700;
				calendar.add(Calendar.DATE, 2);
			} else {
				processingCharge = 500;
				calendar.add(Calendar.DATE, 5);
			}
		} else {
			if (requestDTO.isPriorityRequest()) {
				throw new PriorityException("Accessory can not be a priority request");
			} else {
				processingCharge = 300;
				calendar.add(Calendar.DATE, 5);
			}
		}
		Date deliveryDate = calendar.getTime();
		
		int id = (int) (responseRepository.count() + 1);
		PackagingAndDeliveryDTO packagingAndDeliveryDTO = deliveryClient
				.packagingAndDeliveryDTO(requestDTO.getComponentType(), requestDTO.getQuantity(), token);
		ProcessRequest processRequest = new ProcessRequest(id, requestDTO.getName(), requestDTO.getContactNumber(),
				requestDTO.getComponentName(), requestDTO.getComponentType(), id, requestDTO.isPriorityRequest(), false,false);
		ProcessResponse processResponse = new ProcessResponse(id, processingCharge,
				packagingAndDeliveryDTO.getTotalCharge(), deliveryDate);
		requestRepository.save(processRequest);
		
		totalCharge = processingCharge+packagingAndDeliveryDTO.getTotalCharge();
		return responseRepository.save(processResponse);

	}
	
	public String confirmTransaction(CreditCardDto cardDto,int requestId,String token) throws Exception {
		
		String result = transactionClient.processPayment(token, cardDto,requestId).toString();
		ProcessRequest requestData = requestRepository.findById(requestId).get();
		requestData.setPaymentDone(true);
		return "Deducted "+totalCharge+" Available balance "+result;
		
		
	}
	

}
