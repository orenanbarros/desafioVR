package com.vr.miniautorizadorvr.controllers;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;
import com.vr.miniautorizadorvr.exception.EntityNotFoundException;
import com.vr.miniautorizadorvr.services.VrCardService;

@RestController
public class VrCardController {

	@Autowired
	private VrCardService vrCardService;

	//LISTA TODOS OS CARTOES - GET
	@RequestMapping(value = "/cartoes")
	public List<VrCard> findAll(){
		return vrCardService.findAll();
	}
	
	public ResponseEntity<VrCard> getVrCard(Long cardNumber) {
		try {
			return ResponseEntity.status(HttpStatus.OK).body(vrCardService.findByCardNumber(cardNumber));	
		}catch(EntityNotFoundException e ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
		
	//CRIA NOVO CARTAO - POST
	@PostMapping(value = "/cartoes")
	public ResponseEntity<VrCard> insert(@RequestBody VrCard vrCard){
		
		//Verifica se o numero informado do cartao nao existe na base de dados e se nao existir salva o cartao informado e retorna 201
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(vrCardService.saveNewVrCard(vrCard));	
		}catch(EntityNotFoundException e ) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}		
	}
	
	//RETORNA SALDO DE UM DETERMINADO CARTAO - GET
	@RequestMapping(value = "/cartoes/{numeroCartao}")
	public ResponseEntity<Float> getCardBalance(@PathVariable Long numeroCartao){
		
		return ResponseEntity.status(HttpStatus.OK).body(vrCardService.getCardBalanceByCardNumber(numeroCartao).getBody());
	
	}
	
	//REALIZA TRANSACAO DE DEBITO - POST
	@PostMapping(value = "/transacoes")
	public ResponseEntity<String> doTransaction(@RequestBody VrCardTransaction vrCardTransaction){		
		return vrCardService.doTransaction(vrCardTransaction);
	}
	
	

}
