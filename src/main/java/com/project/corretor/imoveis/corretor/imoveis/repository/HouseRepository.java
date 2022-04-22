package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.House;

@Repository
public interface HouseRepository extends JpaRepository<House, Long>{
	
	List<House> findByStreetId(Long streetId);
}
