package com.vr.miniautorizadorvr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizadorvr.entities.VrCard;

@Repository
public interface VrCardRepository extends JpaRepository<VrCard,Long>{
	
	
	VrCard findByNumeroCartao(String numeroCartao);
		
	VrCard findByNumeroCartaoAndSenhaCartao(String numeroCartao, String senhaCartao );
	
	VrCard findByNumeroCartaoAndSaldoCartaoGreaterThanEqual(String numeroCartao, Float saldoCartao );
	

}
