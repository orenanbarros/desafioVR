package com.vr.miniautorizadorvr.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;
import com.vr.miniautorizadorvr.services.impl.VrCardServiceImpl;
import com.vr.miniautorizadorvr.services.impl.VrCardTransactionServiceImpl;


@SpringBootTest
public class VrCardServiceImplTest {
	
	@Autowired
	private VrCardServiceImpl vCardServiceImpl;
	
	@Autowired
	private VrCardTransactionServiceImpl vrCardTransactionServiceImpl;
	
	//TESTE DE RETORNO DE TODOS OS CARTOES
	@Test
	public void findAll() {
		vCardServiceImpl.findAll();
	}
	
	//TESTE DO SALVAMENTO DE NOVO CARTAO
	
	@Test
	public void saveNewVrCard() {
		VrCard vrCard = new VrCard();
		vrCard.setNumeroCartao("1234567890123456");
		vrCard.setSenhaCartao("Senhacartao123");
		vCardServiceImpl.salvarNovoCartao(vrCard);
	}
	

	//TESTE DE RETORNO DE SALDO PELO NUMERO DO CARTAO
	@Test
	public void getCardBalanceByCardNumber() {
		
		VrCard vrCard = new VrCard();
		vrCard.setNumeroCartao("1234567890123457"); 
		vrCard.setSenhaCartao("Senhacartao123");
		vCardServiceImpl.salvarNovoCartao(vrCard);
		
		String numeroCartao = "1234567890123457";
		ResponseEntity<Float> saldo = vCardServiceImpl.getSaldoByNumeroCartao(numeroCartao);
		System.out.println("CARTAO NÚMERO: " + numeroCartao + " - SALDO: " + saldo.getBody());
	}
	
	
	//TESTE DE EXECUCAO DA TRANSACAO DO CARTAO
	@Test
	public void doTransaction() {
		VrCardTransaction vrCardTransaction = new VrCardTransaction();
		
		VrCard vrCard = new VrCard();
		vrCard.setNumeroCartao("1234567890123458"); 
		vrCard.setSenhaCartao("Senhacartao123");
		vCardServiceImpl.salvarNovoCartao(vrCard);
		
		vrCardTransaction.setnumeroCartao("1234567890123458");
		vrCardTransaction.setsenhaCartao("Senhacartao123");
		vrCardTransaction.setvalor(Float.valueOf("10.00"));
	
		vrCardTransactionServiceImpl.realizarTransacao(vrCardTransaction);
		
		
	}
	 

}
