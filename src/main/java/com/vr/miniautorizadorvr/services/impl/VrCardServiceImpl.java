package com.vr.miniautorizadorvr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;
import com.vr.miniautorizadorvr.exception.EntityNotFoundException;
import com.vr.miniautorizadorvr.exception.EntityUnprocessableException;
import com.vr.miniautorizadorvr.repositories.VrCardRepository;
import com.vr.miniautorizadorvr.services.VrCardService;

@Service
public class VrCardServiceImpl implements VrCardService{

	@Autowired 
	private VrCardRepository repository;
	
	@Value("${msg.insufficient_balance}")
    private String insufficientBalance;
	
	@Value("${msg.invalid_password}")
    private String invalidPassword;
	
	@Value("${msg.inexistent_card}")
    private String inexistentCard;
	
	@Value("${msg.trasactionVr_ok}")
    private String trasactionVrOk;
	
	@Value("${msg.existent_card}")
    private String existentCard;
	
	@Value("${initialCard_Balance}")
    private Float initialCardBalance;
	
	@Override
	public List<VrCard> findAll(){
		return repository.findAll();
	}
			
	@Override
	public VrCard findByCardNumber(Long cardNumber) {
		
		try {			
			return Optional.of(repository.findByCardNumber(cardNumber)).orElseThrow(()-> new EntityNotFoundException());
		}catch(Exception e) {
			throw new EntityNotFoundException(inexistentCard + "-"+ cardNumber) ;
		}
	}
	
	
	@Override
	public ResponseEntity<Float> getCardBalanceByCardNumber(Long cardNumber) {
		
		try {
			VrCard vrCard = Optional.of(repository.findByCardNumber(cardNumber)).orElseThrow(()-> new EntityNotFoundException() );
			return ResponseEntity.status(HttpStatus.OK).body(vrCard.getCardBalance());
		}catch(Exception e) {
			throw new EntityNotFoundException(inexistentCard + cardNumber) ;
		}
		
	}
	
	
	private void checkCardPassword(VrCardTransaction vrCardTransaction) {
		try {
			Optional.of(
					repository.findByCardNumberAndCardPassword(vrCardTransaction.getCardNumber(), vrCardTransaction.getCardPassword())).orElseThrow(()-> new EntityUnprocessableException(invalidPassword) );
			 
		}catch(Exception e) {
			throw new EntityUnprocessableException(invalidPassword) ; 
		}
		
	}
	
	private Float calculateCardBalance(VrCardTransaction vrCardTransaction) {
		try {
			
			VrCard vrCard = Optional.of(
					repository.findByCardNumberAndCardBalanceGreaterThanEqual(vrCardTransaction.getCardNumber(),vrCardTransaction.getValueTransaction())).orElseThrow(()-> new EntityUnprocessableException(insufficientBalance) );
			 
			 
			 return (vrCard.getCardBalance()-vrCardTransaction.getValueTransaction());
					 
		}catch(Exception e) {
			throw new EntityUnprocessableException(insufficientBalance) ;
		}
		
	}
	
	@Override
	public VrCard saveNewVrCard(VrCard vrCard) {
		
		//Verifica se o numero do cartao informado nao existe
		VrCard vrCardAux = repository.findByCardNumber(vrCard.getCardNumber());
		if(vrCardAux == null) {
			//Se o numero do cartao informado nao existe inclui o saldo inicial e salva o novo cartao 
			vrCard.setCardBalance(initialCardBalance);
			return repository.save(vrCard);
		}else {
			throw new EntityUnprocessableException(existentCard) ;
		}
		
	}
	
	@Override
	public ResponseEntity<String> doTransaction(@RequestBody VrCardTransaction vrCardTransaction){
		
		try {
			VrCard vrCard = this.findByCardNumber(vrCardTransaction.getCardNumber());
			
			this.checkCardPassword(vrCardTransaction);
			
			vrCard.setCardBalance(this.calculateCardBalance(vrCardTransaction));
			
			repository.save(vrCard);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(trasactionVrOk);
			
			//SALDO_INSUFICIENTE|SENHA_INVALIDA|CARTAO_INEXISTENTE (dependendo da regra que impediu a autorização)	
		}catch(EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage()) ;
		}catch(EntityUnprocessableException e) {
			throw new EntityUnprocessableException(e.getMessage()) ;
		}

		
	}
	
}

