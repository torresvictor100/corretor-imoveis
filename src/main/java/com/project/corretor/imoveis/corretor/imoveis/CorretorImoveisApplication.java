package com.project.corretor.imoveis.corretor.imoveis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.project.corretor.imoveis.corretor.imoveis.service.S3Service;

@SpringBootApplication
public class CorretorImoveisApplication implements CommandLineRunner {
	
	@Autowired
	private S3Service s3Service;

	public static void main(String[] args) {
		SpringApplication.run(CorretorImoveisApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {		
		s3Service.uploadFile("/home/joao/Imagens/foto.jpg");
	}
}
	
