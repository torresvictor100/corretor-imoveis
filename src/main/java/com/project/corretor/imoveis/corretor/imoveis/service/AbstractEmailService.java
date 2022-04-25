package com.project.corretor.imoveis.corretor.imoveis.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.project.corretor.imoveis.corretor.imoveis.entity.Client;
import com.project.corretor.imoveis.corretor.imoveis.entity.House;
import com.project.corretor.imoveis.corretor.imoveis.entity.Street;

public abstract class AbstractEmailService implements EmailService{
	
	@Value("${default.sender}")
	private String sender;
	@Autowired
	ClientService clientService;
	@Autowired
	StreetService streetService;
	
	@Override
	public void sendOrderConfirmationEmail(House house) {
		
		SimpleMailMessage sm = prepareSimpleMailMessageFromPedido(house);
		
		sendEmail(sm);
	}
	
	
	protected SimpleMailMessage prepareSimpleMailMessageFromPedido(House house) {
		SimpleMailMessage sm = new SimpleMailMessage();
		Client client = clientService.findById(house.getClientId());
		Street street = streetService.findById(house.getStreetHouseId());
		house.setClient(client);
		house.setStreetHouse(street);
		
		
		
		sm.setTo(client.getEmail()); ///////ta bugado resover como pega o id do cliente   kkkkkkkkk########################################
		sm.setFrom(sender);
		sm.setSubject("Cadastro da casa confirmada, imobiliaria souza leão aqui vende");
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(house.toString());
		
		return sm;
	}

}
