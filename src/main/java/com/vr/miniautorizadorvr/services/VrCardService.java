package com.vr.miniautorizadorvr.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;


public interface VrCardService {

	public List<VrCard> findAll();
			
	public VrCard findByCardNumber(Long cardNumber);
			
	public ResponseEntity<Float> getCardBalanceByCardNumber(Long cardNumber);
	
	public VrCard saveNewVrCard(VrCard vrCard);
	
	@Transactional  
	public ResponseEntity<String> doTransaction(@RequestBody VrCardTransaction vrCardTransaction);


}

