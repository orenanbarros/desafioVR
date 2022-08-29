package com.vr.miniautorizadorvr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.exception.EntityNotFoundException;
import com.vr.miniautorizadorvr.exception.EntityUnprocessableException;
import com.vr.miniautorizadorvr.repositories.VrCardRepository;
import com.vr.miniautorizadorvr.services.VrCardService;

@Service
public class VrCardServiceImpl implements VrCardService{

	@Autowired 
	private VrCardRepository repository;
	
	@Autowired
    private MessageSource messageSource;
		
	
	@Override
	public List<VrCard> findAll(){
		return repository.findAll();
	}
			
	@Override
	public VrCard findByNumeroCartao(String numeroCartao) {
		
		try {			
			return Optional.of(repository.findByNumeroCartao(numeroCartao)).orElseThrow(()-> new EntityNotFoundException());
		}catch(Exception e) {
			throw new EntityNotFoundException(messageSource.getMessage("msg.validation.inexistent_card",null,null) + ": " + numeroCartao) ;
		}
	}
	
	
	@Override
	public ResponseEntity<Float> getSaldoByNumeroCartao(String numeroCartao) {
		
		try {
			VrCard vrCard = Optional.of(repository.findByNumeroCartao(numeroCartao)).orElseThrow(()-> new EntityNotFoundException() );
			return ResponseEntity.status(HttpStatus.OK).body(vrCard.getSaldoCartao());
		}catch(Exception e) {
			
			throw new EntityNotFoundException(messageSource.getMessage("msg.validation.inexistent_card",null,null) + ": " +numeroCartao) ;
		}
	}
	
	
	@Override
	@Transactional
	public VrCard salvarNovoCartao(VrCard vrCard) {
		
		//Verifica se o numero do cartao informado nao existe
		
		if(repository.findByNumeroCartao(vrCard.getNumeroCartao()) == null) {
			VrCard vrCardAux = new VrCard();
			vrCardAux.setNumeroCartao(vrCard.getNumeroCartao());
			vrCardAux.setSenhaCartao(vrCard.getSenhaCartao());
			return repository.save(vrCardAux);
		}else {
			throw new EntityUnprocessableException(messageSource.getMessage("msg.validation.existent_card",null,null)) ;
		}
		
	}
	
	
	
}

