package com.vr.miniautorizadorvr.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@Table(name="tb_vrCard")

public class VrCard {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(max=16,min=16,message="{msg.validation.numeroCartaoRequerido}")
	private String numeroCartao;
	
	@NotEmpty (message = "{msg.validation.senhaCartaoRequerido}")
	private String senhaCartao;
	
	private float saldoCartao = com.vr.miniautorizadorvr.config.MiniautorizadorConfig.SALDO_INICIAL_CARTAO;
	
	
	public String getSenhaCartao() {
		return senhaCartao;
	}
	public void setSenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public String getNumeroCartao() {
		return numeroCartao;
	}
	public void setNumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Float getSaldoCartao() {
		return saldoCartao;
	}
	
	public void setSaldoCartao(float saldoCartao) {
		this.saldoCartao = saldoCartao;
	}
}
