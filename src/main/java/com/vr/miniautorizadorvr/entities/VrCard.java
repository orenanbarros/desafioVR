package com.vr.miniautorizadorvr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tb_vrCard")

public class VrCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private Long numeroCartao;
	
	private String senhaCartao;
	
	private Float saldoCartao;
	
	
	public String getSenhaCartao() {
		return senhaCartao;
	}
	public void setSenhaCartao(String cardPassword) {
		this.senhaCartao = cardPassword;
	}
	public Long getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(Long cardNumber) {
		this.numeroCartao = cardNumber;
	}
	public Float getSaldoCartao() {
		return saldoCartao;
	}
	public void setSaldoCartao(Float cardBalance) {
		this.saldoCartao = cardBalance;
	}
}
