package com.vr.miniautorizadorvr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_vrcard")

public class VrCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String cardPassword;
	private Long cardNumber;
	private Float  cardBalance;
	

	
	public String getCardPassword() {
		return cardPassword;
	}
	public void setCardPassword(String cardPassword) {
		this.cardPassword = cardPassword;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public Float getCardBalance() {
		return cardBalance;
	}
	public void setCardBalance(Float cardBalance) {
		this.cardBalance = cardBalance;
	}
}
