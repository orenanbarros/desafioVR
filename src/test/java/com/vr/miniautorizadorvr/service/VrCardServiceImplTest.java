package com.vr.miniautorizadorvr.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.vr.miniautorizadorvr.entities.VrCard;
import com.vr.miniautorizadorvr.services.impl.VrCardServiceImpl;

@SpringBootTest
@RunWith(SpringRunner.class)
@WebAppConfiguration
public class VrCardServiceImplTest {
	
	@Autowired
	private VrCardServiceImpl vrCardServiceImpl;
	
	@Test
	public void findAll() {
		vrCardServiceImpl.findAll();
	}
	
	@Test
	public void findByCardNumber() {
		
		this.saveNewVrCard();
		
		Long cardNumber = Long.parseLong("12345612");
		VrCard vrCard = vrCardServiceImpl.findByCardNumber(cardNumber);
		
		System.out.println(vrCard.getCardNumber());
		System.out.println(vrCard.getCardPassword());
		System.out.println(vrCard.getCardBalance());
		
	}
	
	
	@Test
	public void saveNewVrCard() {
		VrCard vrCard = new VrCard();
		
		vrCard.setCardNumber(Long.getLong("12345612"));
		vrCard.setCardPassword("senhanovacartao");
		vrCard.setCardBalance(Float.valueOf("550.0"));
		
		vrCardServiceImpl.saveNewVrCard(vrCard);
		  
	}

}
