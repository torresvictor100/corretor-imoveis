package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
	
	Optional<Client> findByName(String name);
}
