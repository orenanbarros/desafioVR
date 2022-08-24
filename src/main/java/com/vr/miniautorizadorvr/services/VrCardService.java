package com.vr.miniautorizadorvr.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vr.miniautorizadorvr.entities.VrCard;


public interface VrCardService {

	public List<VrCard> findAll();
			
	public VrCard findByCardNumber(Long cardNumber);
			
	public ResponseEntity<Float> getCardBalanceByCardNumber(Long cardNumber);
	
	public VrCard saveNewVrCard(VrCard vrCard);
	
	

}

