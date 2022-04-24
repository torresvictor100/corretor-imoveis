package com.project.corretor.imoveis.corretor.imoveis.service;

import org.springframework.mail.SimpleMailMessage;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;

public interface EmailService {
	
	void sendOrderConfirmationEmail(House house);

	void sendEmail(SimpleMailMessage msg);

}
