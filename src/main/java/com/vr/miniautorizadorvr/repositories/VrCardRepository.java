package com.vr.miniautorizadorvr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizadorvr.entities.VrCard;

@Repository
public interface VrCardRepository extends JpaRepository<VrCard,Long>{
	
	//Boolean existsByCardNumber(Long numeroCartao);
	
	VrCard findByNumeroCartao(Long numeroCartao);
		
	VrCard findByNumeroCartaoAndSenhaCartao(Long numeroCartao, String senhaCartao );
	
	VrCard findByNumeroCartaoAndSaldoCartaoGreaterThanEqual(Long numeroCartao, Float saldoCartao );
	

}
