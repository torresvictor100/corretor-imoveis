package com.project.corretor.imoveis.corretor.imoveis.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.project.corretor.imoveis.corretor.imoveis.service.EmailService;
import com.project.corretor.imoveis.corretor.imoveis.service.SmtpEmailService;

@Configuration
public class CorsConfig {
	
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("*");
            }
        };
    }
	
	@Bean
	public EmailService emailService() {
		return new SmtpEmailService();
	}
	

}
