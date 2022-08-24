package com.vr.miniautorizadorvr.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vr.miniautorizadorvr.entities.VrCard;

@Repository
public interface VrCardRepository extends JpaRepository<VrCard,Long>{
	
	Boolean existsByCardNumber(Long cardNumber);
	
	VrCard findByCardNumber(Long cardNumber);
	
	VrCard findByCardNumberNot(Long cardNumber);
	
	VrCard findByCardNumberAndCardPassword(Long cardNumber, String cardPassword );
	
	VrCard findByCardNumberAndCardBalanceGreaterThanEqual(Long cardNumber, Float cardBalance );

}
