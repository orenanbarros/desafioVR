package com.vr.miniautorizadorvr.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.vr.miniautorizadorvr.entities.VrCard;


public interface VrCardService {

	public List<VrCard> findAll();
			
	public VrCard findByNumeroCartao(String numeroCartao);
			
	public ResponseEntity<Float> getSaldoByNumeroCartao(String numeroCartao);
	
	public VrCard salvarNovoCartao(VrCard vrCard);
	
	

}

