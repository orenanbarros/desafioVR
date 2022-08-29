package com.vr.miniautorizadorvr.services.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;
import com.vr.miniautorizadorvr.exception.EntityNotFoundException;
import com.vr.miniautorizadorvr.exception.EntityUnprocessableException;
import com.vr.miniautorizadorvr.repositories.VrCardRepository;
import com.vr.miniautorizadorvr.services.VrCardService;
import com.vr.miniautorizadorvr.services.VrCardTransactionService;

@Service
public class VrCardTransactionServiceImpl implements VrCardTransactionService{

	@Autowired 
	private VrCardRepository repository;
	
	@Autowired
	private VrCardService vrCardService;
	
	@Autowired
    private MessageSource messageSource;
	
	private void checkCardPassword(VrCardTransaction vrCardTransaction) {
		try {
			Optional.of(repository.findByNumeroCartaoAndSenhaCartao(vrCardTransaction.getnumeroCartao(), vrCardTransaction.getsenhaCartao())).orElseThrow(()-> new EntityUnprocessableException() );
			 
		}catch(Exception e) {
			throw new EntityUnprocessableException(messageSource.getMessage("msg.validation.invalid_password",null,null)) ; 
		}
		
	}
	
	private Float calculateCardBalance(VrCardTransaction vrCardTransaction) {
		try {
			
			VrCard vrCard = Optional.of(repository.findByNumeroCartaoAndSaldoCartaoGreaterThanEqual(vrCardTransaction.getnumeroCartao(),vrCardTransaction.getvalor())).orElseThrow(()-> new EntityUnprocessableException() );
			  
			return (vrCard.getSaldoCartao()-vrCardTransaction.getvalor());
					 
		}catch(Exception e) {
			throw new EntityUnprocessableException(messageSource.getMessage("msg.validation.insufficient_balance",null,null)) ;
		}
		
	}
	
	@Override
	@Transactional
	public ResponseEntity<String> realizarTransacao(@RequestBody VrCardTransaction vrCardTransaction){
		
		try {
			VrCard vrCard = vrCardService.findByNumeroCartao(vrCardTransaction.getnumeroCartao());
			
			this.checkCardPassword(vrCardTransaction);
			
			vrCard.setSaldoCartao(this.calculateCardBalance(vrCardTransaction));
			
			repository.save(vrCard);
			
			return ResponseEntity.status(HttpStatus.CREATED).body(messageSource.getMessage("msg.validation.trasactionVr_ok",null,null));
			
			//SALDO_INSUFICIENTE|SENHA_INVALIDA|CARTAO_INEXISTENTE (dependendo da regra que impediu a autorização)	
		}catch(EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage()) ;
		}catch(EntityUnprocessableException e) {
			throw new EntityUnprocessableException(e.getMessage()) ;
		}

		
	}

	
	
}

