package com.vr.miniautorizadorvr.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.entities.VrCardTransaction;
import com.vr.miniautorizadorvr.services.impl.VrCardServiceImpl;
import com.vr.miniautorizadorvr.services.impl.VrCardTransactionServiceImpl;


@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration

public class VrCardServiceImplTest {
	
	@Autowired
	private VrCardServiceImpl vCardServiceImpl;
	
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
		vrCard.setNumeroCartao(Long.parseLong("123456789"));
		vrCard.setSenhaCartao("Senhacartao123");
		vCardServiceImpl.salvarNovoCartao(vrCard);
	}
	

	//TESTE DE RETORNO DE SALDO PELO NUMERO DO CARTAO
	@Test
	public void getCardBalanceByCardNumber() {
		
		Long numeroCartao = Long.parseLong("123456789");
		vCardServiceImpl.getSaldoByNumeroCartao(numeroCartao);
		
	}
	
	
	//TESTE DE EXECUCAO DA TRANSACAO DO CARTAO
	@Test
	public void doTransaction() {
		VrCardTransaction vrCardTransaction = new VrCardTransaction();
		
		vrCardTransaction.setnumeroCartao(Long.parseLong("123456789"));
		vrCardTransaction.setsenhaCartao("1234");
		vrCardTransaction.setvalor(Float.valueOf("10.00"));
	
		vrCardTransactionServiceImpl.realizarTransacao(vrCardTransaction);
		
		
	}
	 

}
