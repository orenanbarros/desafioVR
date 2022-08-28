package com.vr.miniautorizadorvr.services;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.vr.miniautorizadorvr.entities.VrCardTransaction;


public interface VrCardTransactionService {
						
	@Transactional  
	public ResponseEntity<String> realizarTransacao(@RequestBody VrCardTransaction vrCardTransaction);


}

