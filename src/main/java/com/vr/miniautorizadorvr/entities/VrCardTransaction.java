package com.vr.miniautorizadorvr.entities;

public class VrCardTransaction {
	
	private String cardPassword;
	private Long cardNumber;
	private Float  valueTransaction;
	
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
	public Float getValueTransaction() {
		return valueTransaction;
	}
	public void setValueTransaction(Float valueTransaction) {
		this.valueTransaction = valueTransaction;
	}
	
}
