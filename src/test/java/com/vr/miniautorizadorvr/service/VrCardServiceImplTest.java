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
	private VrCardServiceImpl vCardServiceImpl;
	
	@Test
	public void findAll() {
		vCardServiceImpl.findAll();
	}
	
	public void inserNewCard() {
		VrCard vrCard = new VrCard();
		vrCard.setCardNumber(Long.parseLong("123456789"));
		vrCard.setCardPassword("Senhacartao123");
		vrCard.setCardBalance(Float.valueOf("500.00"));
		vCardServiceImpl.saveNewVrCard(vrCard);
	}
	 

}
