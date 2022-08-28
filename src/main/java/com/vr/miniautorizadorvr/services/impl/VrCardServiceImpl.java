package com.vr.miniautorizadorvr.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.vr.miniautorizadorvr.entities.VrCard;
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
	public VrCard findByNumeroCartao(Long numeroCartao) {
		
		try {			
			return Optional.of(repository.findByNumeroCartao(numeroCartao)).orElseThrow(()-> new EntityNotFoundException());
		}catch(Exception e) {
			throw new EntityNotFoundException(inexistentCard + ":"+ numeroCartao) ;
		}
	}
	
	
	@Override
	public ResponseEntity<Float> getSaldoByNumeroCartao(Long numeroCartao) {
		
		try {
			VrCard vrCard = Optional.of(repository.findByNumeroCartao(numeroCartao)).orElseThrow(()-> new EntityNotFoundException() );
			return ResponseEntity.status(HttpStatus.OK).body(vrCard.getSaldoCartao());
		}catch(Exception e) {
			throw new EntityNotFoundException(inexistentCard + numeroCartao) ;
		}
	}
	
	
	@Override
	public VrCard salvarNovoCartao(VrCard vrCard) {
		
		//Verifica se o numero do cartao informado nao existe
		VrCard vrCardAux = repository.findByNumeroCartao(vrCard.getNumeroCartao());
		if(vrCardAux == null) {
			//Se o numero do cartao informado nao existe inclui o saldo inicial e salva o novo cartao 
			vrCard.setSaldoCartao(initialCardBalance);
			return repository.save(vrCard);
		}else {
			throw new EntityUnprocessableException(existentCard) ;
		}
		
	}
	
	
	
}

