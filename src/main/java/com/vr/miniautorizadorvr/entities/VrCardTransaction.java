package com.vr.miniautorizadorvr.entities;

public class VrCardTransaction {
		
	//@NotEmpty(message = "O campo numeroCartao deve ser preenchido")
	
	private Long numeroCartao;
	
	private String senhaCartao;
	
	private Float valor;
	
	public String getsenhaCartao() {
		return senhaCartao;
	}
	public void setsenhaCartao(String senhaCartao) {
		this.senhaCartao = senhaCartao;
	}
	public Long getnumeroCartao() {
		return numeroCartao;
	}
	public void setnumeroCartao(Long numeroCartao) {
		this.numeroCartao = numeroCartao;
	}
	public Float getvalor() {
		return valor;
	}
	public void setvalor(Float valor) {
		this.valor = valor;
	}
	
}
