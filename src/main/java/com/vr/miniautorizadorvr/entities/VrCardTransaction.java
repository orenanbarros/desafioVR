package com.vr.miniautorizadorvr.entities;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class VrCardTransaction {
		
	@Size(max=16,min=16,message="{msg.validation.numeroCartaoRequerido}")
	private String numeroCartao;
	
	@NotEmpty (message = "{msg.validation.senhaCartaoRequerido}")
	private String senhaCartao;
	
	@NotNull(message = "{msg.validation.numericField.notnull}")
	@Positive(message = "{msg.validation.numericField.positive}")
	private Float valor;
	
	public String getsenhaCartao() {
		return senhaCartao;
	}
	public void setsenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public String getnumeroCartao() {
		return numeroCartao;
	}
	public void setnumeroCartao(String numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Float getvalor() {
		return valor;
	}
	public void setvalor(Float valor) {
		this.valor = valor;
	}
	
}
