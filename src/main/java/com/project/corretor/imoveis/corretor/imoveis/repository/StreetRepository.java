package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.Street;

@Repository
public interface StreetRepository extends JpaRepository<Street, Long> {
	
	Optional<Street> findByName(String name);
	
	Optional<Street> findByCep(String name);
	

}
