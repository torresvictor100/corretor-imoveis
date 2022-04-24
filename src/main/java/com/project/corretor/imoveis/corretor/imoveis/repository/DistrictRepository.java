package com.project.corretor.imoveis.corretor.imoveis.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.corretor.imoveis.corretor.imoveis.entity.District;

@Repository
public interface DistrictRepository extends JpaRepository<District, Long>{
	
	List<District> findByNameContainingIgnoreCase(String name);
	
	List<District> findByCityId(Long cityId);
	
	Optional<District> findByName(String name);

}
