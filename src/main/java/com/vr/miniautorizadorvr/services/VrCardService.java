package com.vr.miniautorizadorvr.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vr.miniautorizadorvr.entities.VrCard;


public interface VrCardService {

	public List<VrCard> findAll();
			
	public VrCard findByNumeroCartao(Long numeroCartao);
			
	public ResponseEntity<Float> getSaldoByNumeroCartao(Long numeroCartao);
	
	public VrCard salvarNovoCartao(VrCard vrCard);
	
	

}

